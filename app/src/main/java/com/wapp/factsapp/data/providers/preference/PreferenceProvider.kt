package com.wapp.factsapp.data.providers.preference

interface PreferenceProvider {

    fun setFirstUseTo(value: Boolean)
    fun isFirstUse():Boolean
    fun setPickedCategories(list:Set<String>)
    fun getPickedCategories():List<String>
    fun setDailyWorkLaunchedTo(value:Boolean)
    fun dailyWorkLaunched(): Boolean
    fun setClickNumber(number: Int)
    fun getClickNumber():Int
    fun setAdsConsent(number:Int)
    fun getAdsConsent():Int
    fun setNotificationStatus(status:Boolean)
    fun getNotificationStatus():Boolean
    fun setGoogleToken(token:String)
    fun getGoogleToken():String
}