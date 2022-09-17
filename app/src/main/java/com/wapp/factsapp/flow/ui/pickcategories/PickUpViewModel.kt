package com.wapp.factsapp.flow.ui.pickcategories


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.models.Category
import com.wapp.factsapp.utils.categoriesToPickList


class PickUpViewModel @ViewModelInject constructor(private val preferenceProvider: PreferenceProvider):ViewModel() {

    private val _pickUpList = MutableLiveData<List<Category>>()
    val pickUpList: LiveData<List<Category>>
        get() = _pickUpList

    var categoriesNameList = mutableListOf<String>()

    init {
        _pickUpList.value = getPickUpList()
        categoriesNameList = preferenceProvider.getPickedCategories().toMutableList()
    }

    fun addOrRemoveFromPickUpList(category: Category){
        category.let {
            if(it.isPicked) categoriesNameList.add(it.name) else categoriesNameList.remove(it.name)
        }
    }

    private fun getPickUpList(): List<Category>{
        val list = mutableListOf<Category>()
        categoriesToPickList().forEach {
            if(it.name !in preferenceProvider.getPickedCategories())
                it.isPicked = false
            list.add(it)
        }
        return list
    }

    fun updatePickUpList(list:List<String>){
        preferenceProvider.setPickedCategories(list.toSet())
    }
}