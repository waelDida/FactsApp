package com.wapp.factsapp.flow.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wapp.factsapp.data.repositories.factsDataRepository.FactsDataRepo
import com.wapp.factsapp.models.Fact
import kotlinx.coroutines.Dispatchers

class FavoriteViewModel @ViewModelInject constructor(private val factsDataRepo: FactsDataRepo):ViewModel() {

    val likedFacts = liveData(Dispatchers.IO) {
        val data = factsDataRepo.getFavoriteFacts()
        emit(data)
    }
}