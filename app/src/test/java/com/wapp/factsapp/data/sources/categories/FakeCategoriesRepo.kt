package com.wapp.factsapp.data.sources.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wapp.factsapp.data.repositories.categoriesDataRepository.CategoriesDataRepo
import com.wapp.factsapp.models.Category

class FakeCategoriesRepo: CategoriesDataRepo {

    private var list = mutableListOf<Category>()
    private val liveList = MutableLiveData<List<Category>>()
    override suspend fun insertAllCategories(categories: List<Category>) {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): List<Category> {
        TODO("Not yet implemented")
    }

    override fun getAllCategories(): LiveData<List<Category>> {
        liveList.value = list
        return liveList
    }

    override suspend fun updateCategory(category: Category) {
        TODO("Not yet implemented")
    }
}