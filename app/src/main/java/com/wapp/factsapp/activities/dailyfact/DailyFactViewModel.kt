package com.wapp.factsapp.activities.dailyfact

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.repositories.factsDataRepository.FactsDataRepo
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.models.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DailyFactViewModel @ViewModelInject constructor(private val userDataRepo: UserDataRepo,
                                                      private val factsDataRepo: FactsDataRepo,
                                                      private val firebaseProvider: FirebaseProvider,
                                                      preferenceProvider: PreferenceProvider): ViewModel() {

    val consentStatus = preferenceProvider.getAdsConsent()

    private val _gotIt = MutableLiveData<Boolean>()
    val gotIt: LiveData<Boolean>
        get() = _gotIt

    private val _details = MutableLiveData<Boolean>()
    val details: LiveData<Boolean>
        get() = _details

    private val _share = MutableLiveData<Fact>()
    val share: LiveData<Fact>
        get() = _share

    private val _like = MutableLiveData<Boolean>()
    val like: LiveData<Boolean>
        get() = _like

    private val _bookMark = MutableLiveData<Boolean>()
    val bookmark: LiveData<Boolean>
    get() = _bookMark

   private val _dailyFact = MutableLiveData<Fact>()
    val dailyFact: LiveData<Fact>
        get() = _dailyFact


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDataRepo.getUserFromRoom(firebaseProvider.getCurrentUserId())
            val x = factsDataRepo.getFact(user.factNum.minus(1).toString())
            withContext(Dispatchers.Main){
                _dailyFact.value = x
            }
        }
    }

  /* fun onLikeDislike(){
        _dailyFact.value!!.isLiked = !_dailyFact.value!!.isLiked
        viewModelScope.launch(Dispatchers.IO) {
            factsDataRepo.updateLocalFact(_dailyFact.value!!)
            withContext(Dispatchers.Main){
                _like.value = true
            }
        }
    }

    fun onBookMarkDisBookMark(){
        _dailyFact.value!!.isBookmarked = !_dailyFact.value!!.isBookmarked
        viewModelScope.launch(Dispatchers.IO) {
            factsDataRepo.updateLocalFact(dailyFact.value!!)
            withContext(Dispatchers.Main){
                _bookMark.value = true
            }
        }
    }*/

    fun onGotItClick(){
        _gotIt.value = true
    }

    fun onDetailsClick(){
        _details.value = true
    }

   /* fun onShareClick(){
        _share.value = dailyFact.value
    }*/
}