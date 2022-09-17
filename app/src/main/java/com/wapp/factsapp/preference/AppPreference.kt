package com.wapp.factsapp.preference

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppPreference @Inject constructor(@ApplicationContext context: Context) {

    companion object{
        const val prefName = "appPref"
        const val IS_FIRST_USE = "is_first_use"
        const val PICKED_CATEGORIES = "picked_categories"
        const val DAILY_WORK_STATUS = "daily_work_status"
        const val ADS_CLICK_NUMBER = "ads_click_number"
        const val ADS_CONSENT_TAG = "ads_consent_tag"
        const val NOTIFICATION_STATUS = "notification_status"
        const val GOOGLE_TOKEN = "google_token"
    }

    private var pref = context.applicationContext.getSharedPreferences(prefName,Context.MODE_PRIVATE)

    fun setFirstUse(value:Boolean){
        with(pref.edit()){
            putBoolean(IS_FIRST_USE,value).apply()
        }
    }

    fun isFirstUse() = pref.getBoolean(IS_FIRST_USE,true)

    fun setPickedCategories(list:Set<String>){
        with(pref.edit()){
            putStringSet(PICKED_CATEGORIES,list).apply()
        }
    }

    fun getPickedCategories(): Set<String>? = pref.getStringSet(PICKED_CATEGORIES, emptySet())

    fun setDailyWorkLaunchedTo(value:Boolean){
        with(pref.edit()){
            putBoolean(DAILY_WORK_STATUS,value).apply()
        }
    }

    fun dailyWorkLaunched() = pref.getBoolean(DAILY_WORK_STATUS,false)

    fun setClickNumber(number: Int){
        with(pref.edit()){
            putInt(ADS_CLICK_NUMBER,number).apply()
        }
    }

    fun getClickNumber() = pref.getInt(ADS_CLICK_NUMBER,0)

    fun setAdsConsent(number: Int){
        with(pref.edit()){
            putInt(ADS_CONSENT_TAG,number).apply()
        }
    }

    fun getAdsConsent() = pref.getInt(ADS_CONSENT_TAG,1)

    fun setNotificationStatus(status: Boolean){
        with(pref.edit()){
            putBoolean(NOTIFICATION_STATUS,status).apply()
        }
    }

    fun getNotificationStatus() = pref.getBoolean(NOTIFICATION_STATUS,true)

    fun setGoogleToken(token: String){
        with(pref.edit()){
            putString(GOOGLE_TOKEN,token).apply()
        }
    }

    fun getGoogleToken() = pref.getString(GOOGLE_TOKEN,"")

}