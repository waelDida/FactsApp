package com.wapp.factsapp.data.providers.preference

import com.wapp.factsapp.preference.AppPreference
import javax.inject.Inject

class PreferenceProviderImp @Inject constructor(private val preference: AppPreference):PreferenceProvider {

    override fun setFirstUseTo(value: Boolean) {
        preference.setFirstUse(value)
    }

    override fun isFirstUse() = preference.isFirstUse()

    override fun setPickedCategories(list: Set<String>) {
        preference.setPickedCategories(list)
    }

    override fun getPickedCategories(): List<String> {
        return preference.getPickedCategories()!!.toList()
    }

    override fun setDailyWorkLaunchedTo(value: Boolean) {
        preference.setDailyWorkLaunchedTo(value)
    }

    override fun dailyWorkLaunched(): Boolean {
        return preference.dailyWorkLaunched()
    }

    override fun setClickNumber(number: Int) {
        preference.setClickNumber(number)
    }

    override fun getClickNumber(): Int {
        return preference.getClickNumber()
    }

    override fun setAdsConsent(number: Int) {
        preference.setAdsConsent(number)
    }

    override fun getAdsConsent(): Int {
        return preference.getAdsConsent()
    }

    override fun setNotificationStatus(status: Boolean) {
        preference.setNotificationStatus(status)
    }

    override fun getNotificationStatus(): Boolean {
        return preference.getNotificationStatus()
    }

    override fun setGoogleToken(token: String) {
        preference.setGoogleToken(token)
    }

    override fun getGoogleToken(): String {
        return preference.getGoogleToken()!!
    }
}