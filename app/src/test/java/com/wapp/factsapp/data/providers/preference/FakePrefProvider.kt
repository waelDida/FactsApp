package com.wapp.factsapp.data.providers.preference

class FakePrefProvider: PreferenceProvider {

    private var firstUseValue: Boolean = true
    private var pickedCategories =  mutableListOf<String>()
    private var workLaunched: Boolean = false
    private lateinit var googleToken: String

    override fun setFirstUseTo(value: Boolean) {
        firstUseValue = value
    }

    override fun isFirstUse() = firstUseValue

    override fun setPickedCategories(list: Set<String>) {
        pickedCategories = list.toMutableList()
    }

    override fun getPickedCategories(): List<String> {
        return pickedCategories.toList()
    }

    override fun setDailyWorkLaunchedTo(value: Boolean) {
        workLaunched = value
    }

    override fun dailyWorkLaunched() = workLaunched
    override fun setClickNumber(number: Int) {
        TODO("Not yet implemented")
    }

    override fun getClickNumber(): Int {
        return 1
    }

    override fun setAdsConsent(number: Int) {
        TODO("Not yet implemented")
    }

    override fun getAdsConsent(): Int {
        return 1
    }

    override fun setNotificationStatus(status: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getNotificationStatus(): Boolean {
        return true
    }

    override fun setGoogleToken(token: String) {
        googleToken = token
    }

    override fun getGoogleToken(): String {
        return googleToken
    }
}