package com.wapp.factsapp.flow.ui.home

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
import com.wapp.factsapp.utils.REMOTE_COINS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeViewModel @ViewModelInject constructor(private val factsDataRepo: FactsDataRepo,
                                                 private val preferenceProvider: PreferenceProvider,
                                                 private val userDataRepo: UserDataRepo,
                                                 private val firebaseProvider: FirebaseProvider) : ViewModel() {

    companion object{
        const val FACTS_NUMBER = 20
    }

    private var finalList = listOf<Fact>()

    val currentUser = userDataRepo.getLiveUserFromRoom(firebaseProvider.getCurrentUserId())
    var adsClicks: Int


    private val _facts = MutableLiveData<List<Fact>>()
    val facts: LiveData<List<Fact>>
        get() = _facts

    init {
        getFacts()
        adsClicks = preferenceProvider.getClickNumber()
    }

     fun getFacts(){
        viewModelScope.launch(Dispatchers.IO) {
             val f = factsDataRepo.getAllFacts()
                     .filter { it.category in preferenceProvider.getPickedCategories() && !it.isLiked && !it.isBookmarked}
                     .shuffled()
            withContext(Dispatchers.Main){
                finalList = if(f.size > FACTS_NUMBER)
                    f.subList(0, FACTS_NUMBER)
                else
                    f.subList(0,f.size)
                _facts.value = finalList
            }
        }
    }

    fun attributeTheGiftCoins(){
        viewModelScope.launch(Dispatchers.IO) {
            userDataRepo.updateUserField(firebaseProvider.getCurrentUserId(), REMOTE_COINS,100)
            //userDataRepo.updateUserIntoRoom(userDataRepo.getCurrentUserFromFireStore(firebaseProvider.getCurrentUserId()))
            userDataRepo.getCurrentUserFromFireStore(firebaseProvider.getCurrentUserId())?.let {
                userDataRepo.updateUserIntoRoom(it)
            }
        }
    }

    fun setAdsClick(number: Int){
        preferenceProvider.setClickNumber(number)
    }
}