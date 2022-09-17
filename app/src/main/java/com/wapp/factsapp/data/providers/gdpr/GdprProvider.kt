package com.wapp.factsapp.data.providers.gdpr

interface GdprProvider {
    fun showGdprForm()
    fun isUserInEurope():Boolean
    fun requestConsent()
}