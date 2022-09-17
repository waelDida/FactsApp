package com.wapp.factsapp.flow.ui.profile

import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.models.User
import com.wapp.factsapp.resource.Resource
import com.wapp.factsapp.utils.REMOTE_COINS
import com.wapp.factsapp.utils.REMOTE_IMAGE_URL
import com.wapp.factsapp.utils.SIGN_IN_WITH_EMAIL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileViewModel @ViewModelInject constructor(private val firebaseProvider: FirebaseProvider,
                                                    private val userDataRepo: UserDataRepo,
                                                    private val preferenceProvider: PreferenceProvider): ViewModel() {

    val currentUser: LiveData<User?> = userDataRepo.getLiveUserFromRoom(firebaseProvider.getCurrentUserId())
    val notificationStatus = preferenceProvider.getNotificationStatus()
    val consentStatus: Int = preferenceProvider.getAdsConsent()


    private val _uploadImage = MutableLiveData<Boolean>()
    val uploadImage: LiveData<Boolean>
        get() = _uploadImage

    private val _imageSaved = MutableLiveData<Boolean>()
    val imageSaved: LiveData<Boolean>
        get() = _imageSaved

    private val _pickCategories = MutableLiveData<Boolean>()
    val pickCategories: LiveData<Boolean>
        get() = _pickCategories

    private val _gdprClick = MutableLiveData<Boolean>()
    val gdprClick: LiveData<Boolean>
        get() = _gdprClick

    private val _rewardAdsClick = MutableLiveData<Boolean>()
    val rewardAdsClick: LiveData<Boolean>
        get() = _rewardAdsClick

    private val _deleteAccount = MutableLiveData<Boolean>()
    val deleteAccount: LiveData<Boolean>
        get() = _deleteAccount

    private val _reAuthenticate = MutableLiveData<Boolean>()
    val reAuthenticate: LiveData<Boolean>
        get() = _reAuthenticate

    private val _deleteWithEmail = MutableLiveData<Boolean>()
    val deleteWithEmail:LiveData<Boolean>
        get() = _deleteWithEmail


    fun uploadImageToFirebase(uri:Uri?){
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDataRepo.getUserFromRoom(firebaseProvider.getCurrentUserId())
            firebaseProvider.deleteDetailedStorageReference(user.imgUrl)
            val result =  firebaseProvider.uploadImageToFirebase(uri)
            withContext(Dispatchers.Main){
                when(result){
                    is Resource.Success -> {
                        saveImageInFireStoreAndLocally(result.data!!)
                    }
                    else -> _imageSaved.value = false
                }
            }
        }
    }

    fun saveNotificationStatus(status:Boolean){
        preferenceProvider.setNotificationStatus(status)
    }

    private fun saveImageInFireStoreAndLocally(uri: String){
        viewModelScope.launch(Dispatchers.IO) {
            userDataRepo.updateUserField(firebaseProvider.getCurrentUserId(), REMOTE_IMAGE_URL,uri)
            val y = userDataRepo.getUserFromRoom(firebaseProvider.getCurrentUserId())
            y.imgUrl = uri
            userDataRepo.updateUserIntoRoom(y)
            withContext(Dispatchers.Main){
                _imageSaved.value = true
            }
        }
    }

    fun attributeTheGiftCoins(){
        viewModelScope.launch(Dispatchers.IO) {
            userDataRepo.getCurrentUserFromFireStore(firebaseProvider.getCurrentUserId())?.let {
                val coins = it.coins
                val y = coins.plus(100)
                userDataRepo.updateUserField(firebaseProvider.getCurrentUserId(), REMOTE_COINS,y)
                userDataRepo.updateUserIntoRoom(it)
            }
        }
    }

    fun reAuthenticateUser(){
        if(firebaseProvider.getSignInMethod() == SIGN_IN_WITH_EMAIL)
            _deleteWithEmail.value = true
        else{
            viewModelScope.launch(Dispatchers.IO) {
                firebaseProvider.reAuthenticate("_",preferenceProvider.getGoogleToken())
                withContext(Dispatchers.Main){
                    _reAuthenticate.value = true
                }
            }
        }
    }

    fun reAuthenticationEmailUser(password: String){
        viewModelScope.launch(Dispatchers.IO) {
            firebaseProvider.reAuthenticate(password,"_")
            withContext(Dispatchers.Main){
                _reAuthenticate.value = true
            }
        }
    }

    fun setConsentStatus(value:Int){
        preferenceProvider.setAdsConsent(value)
    }

    fun onUploadImageClick(){
        _uploadImage.value = true
    }

    fun onUploadImageClicked(){
        _uploadImage.value = false
    }

    fun onPickCategoriesClick(){
        _pickCategories.value = true
    }

    fun onPickCategoriesClicked(){
        _pickCategories.value = false
    }

    fun onGdprClick(){
        _gdprClick.value = true
    }

    fun onGdprClicked(){
        _gdprClick.value = false
    }

    fun onRewardAdsClick(){
        _rewardAdsClick.value = true
    }

    fun onRewardAdsClicked(){
        _rewardAdsClick.value = false
    }

    fun onDeleteClicked(){
        _deleteAccount.value = true
    }
}