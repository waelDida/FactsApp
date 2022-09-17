package com.wapp.factsapp.flow.ui.factsdetails

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.wapp.factsapp.data.repositories.factsDataRepository.FactsDataRepo
import com.wapp.factsapp.models.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FactsDetailsViewModel @ViewModelInject constructor(@Assisted savedStateHandle: SavedStateHandle,
                                                         private val factsDataRepo: FactsDataRepo): ViewModel() {


    var currentFact = factsDataRepo.getLocalFact(savedStateHandle["fact_id"]!!)

    private val _shareFact = MutableLiveData<Fact?>()
    val shareFact: LiveData<Fact?>
        get() = _shareFact

    fun onLikeDislike(){
        currentFact.value!!.isLiked = !currentFact.value!!.isLiked
        viewModelScope.launch(Dispatchers.IO) {
            factsDataRepo.updateLocalFact(currentFact.value!!)
        }
    }

     fun onBookMarkDisBookMark(){
         currentFact.value!!.isBookmarked = !currentFact.value!!.isBookmarked
         viewModelScope.launch(Dispatchers.IO) {
             factsDataRepo.updateLocalFact(currentFact.value!!)
         }
    }

    fun onShare(){
        _shareFact.value = currentFact.value
    }

    fun onShareClicked(){
        _shareFact.value = null
    }


}