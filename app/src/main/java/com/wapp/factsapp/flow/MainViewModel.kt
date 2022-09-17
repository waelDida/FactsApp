package com.wapp.factsapp.flow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.models.User

class MainViewModel @ViewModelInject constructor(
        firebaseProvider: FirebaseProvider,
        userDataRepo: UserDataRepo,
        preferenceProvider: PreferenceProvider):ViewModel() {
    val currentUser: LiveData<User?> = userDataRepo.getLiveUserFromRoom(firebaseProvider.getCurrentUserId())
    val consentStatus: Int = preferenceProvider.getAdsConsent()

}