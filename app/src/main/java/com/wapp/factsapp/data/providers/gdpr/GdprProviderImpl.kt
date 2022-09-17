package com.wapp.factsapp.data.providers.gdpr

import android.content.Context
import com.google.ads.consent.*
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.MalformedURLException
import java.net.URL
import javax.inject.Inject


class GdprProviderImpl @Inject constructor(@ApplicationContext val context: Context,
                                           private val preferenceProvider: PreferenceProvider): GdprProvider {
    private var form: ConsentForm? = null
    private val consentInformation = ConsentInformation.getInstance(context)

    override fun showGdprForm() {
      //  consentInformation.debugGeography = DebugGeography.DEBUG_GEOGRAPHY_EEA
      //  if(consentInformation.isRequestLocationInEeaOrUnknown)
            requestConsentInfo()
    }

    override fun isUserInEurope(): Boolean {
        //consentInformation.debugGeography = DebugGeography.DEBUG_GEOGRAPHY_EEA
        return consentInformation.isRequestLocationInEeaOrUnknown
    }

    private fun requestConsentInfo(){
        val publisherIds = arrayOf("pub-9789xxxxxxxxxx")
       consentInformation.requestConsentInfoUpdate(publisherIds,
           object : ConsentInfoUpdateListener {
               override fun onConsentInfoUpdated(consentStatus: ConsentStatus?) {
                   when (consentStatus) {
                       ConsentStatus.PERSONALIZED -> {
                           consentInformation.consentStatus = ConsentStatus.PERSONALIZED
                           preferenceProvider.setAdsConsent(1)
                       }
                       ConsentStatus.NON_PERSONALIZED -> {
                           consentInformation.consentStatus = ConsentStatus.NON_PERSONALIZED
                           preferenceProvider.setAdsConsent(2)
                       }
                       ConsentStatus.UNKNOWN -> {
                           requestConsent()
                       }
                   }
               }

               override fun onFailedToUpdateConsentInfo(reason: String?) {
                   TODO("Not yet implemented")
               }

           })
    }

    override fun requestConsent() {
        var privacyUrl: URL? = null
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = URL("https://sites.google.com/view/privacy-policy-wapp/accueil")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            // Handle error.
        }
        form = ConsentForm.Builder(context,privacyUrl).withListener(object : ConsentFormListener(){
            override fun onConsentFormLoaded() {
                form?.show()
            }

            override fun onConsentFormClosed(
                consentStatus: ConsentStatus?,
                userPrefersAdFree: Boolean?
            ) {
                when(consentStatus){
                    ConsentStatus.PERSONALIZED -> {
                        consentInformation.consentStatus = ConsentStatus.PERSONALIZED
                        preferenceProvider.setAdsConsent(1)

                    }
                    ConsentStatus.NON_PERSONALIZED -> {
                        consentInformation.consentStatus = ConsentStatus.NON_PERSONALIZED
                        preferenceProvider.setAdsConsent(2)
                    }
                    ConsentStatus.UNKNOWN -> {
                        consentInformation.consentStatus = ConsentStatus.UNKNOWN
                    }
                }
            }

        }).withPersonalizedAdsOption()
            .withNonPersonalizedAdsOption()
            .build()

        form?.load()

    }
}