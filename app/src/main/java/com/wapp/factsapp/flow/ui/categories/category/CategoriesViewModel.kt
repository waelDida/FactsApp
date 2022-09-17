package com.wapp.factsapp.flow.ui.categories.category

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.repositories.categoriesDataRepository.CategoriesDataRepo
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.models.Category
import com.wapp.factsapp.models.User
import com.wapp.factsapp.utils.REMOTE_COINS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesViewModel @ViewModelInject constructor(private val userDataRepo: UserDataRepo,
                                                       private val firebaseProvider: FirebaseProvider,
                                                       private val categoriesDataRepo: CategoriesDataRepo,
                                                       private val preferenceProvider: PreferenceProvider): ViewModel() {

    private val _unlockWithSuccess = MutableLiveData<Boolean>()
    val unlockWithSuccess: LiveData<Boolean>
        get() = _unlockWithSuccess

    lateinit var user:User

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val x = userDataRepo.getUserFromRoom(firebaseProvider.getCurrentUserId())
            withContext(Dispatchers.Main){
                user = x
            }
        }
    }

    var categories = categoriesDataRepo.getAllCategories()


    fun unlockTopic(user: User, category: Category){
        val diff = user.coins.minus(category.value)
        if(diff >= 0){
            viewModelScope.launch(Dispatchers.IO) {
                user.coins = diff
                category.isLocked = false
                val list = preferenceProvider.getPickedCategories().toMutableList()
                list.add(category.name)
                preferenceProvider.setPickedCategories(list.toSet())
                categoriesDataRepo.updateCategory(category)
                userDataRepo.updateUserIntoRoom(user)
                userDataRepo.updateUserField(firebaseProvider.getCurrentUserId(), REMOTE_COINS,diff)
                withContext(Dispatchers.Main){
                    _unlockWithSuccess.value = true
                }
            }
        }
        else{
            _unlockWithSuccess.value = false
        }
    }
}