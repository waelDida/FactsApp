package com.wapp.factsapp.flow.ui.categories.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.repositories.factsDataRepository.FactsDataRepo
import com.wapp.factsapp.flow.ui.home.HomeViewModel
import com.wapp.factsapp.models.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel @ViewModelInject constructor(@Assisted val savedStateHandle: SavedStateHandle,
                                                   private val factsDataRepo: FactsDataRepo,
                                                   private val preferenceProvider: PreferenceProvider):  ViewModel() {

    companion object{
        const val FACTS_NUMBER = 5
    }

    var adsClicks: Int = preferenceProvider.getClickNumber()

    private val _factsByCategory = MutableLiveData<List<Fact>>()
    val factsByCategory: LiveData<List<Fact>>
        get() = _factsByCategory

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val list = factsDataRepo.getFactsByCategory(savedStateHandle["category"]!!).shuffled()
            if(list.size > HomeViewModel.FACTS_NUMBER)
                list.subList(0, HomeViewModel.FACTS_NUMBER)
            else
                list.subList(0,list.size)
            withContext(Dispatchers.Main){
                _factsByCategory.value = list
            }
        }
    }

    fun setAdsClick(number: Int){
        preferenceProvider.setClickNumber(number)
    }
}