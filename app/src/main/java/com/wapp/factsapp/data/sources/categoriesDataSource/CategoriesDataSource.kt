package com.wapp.factsapp.data.sources.categoriesDataSource

import androidx.lifecycle.LiveData
import com.wapp.factsapp.models.Category

interface CategoriesDataSource {

    suspend fun insertAllCategories(categories: List<Category>)
    suspend fun getCategories():List<Category>
    fun getAllCategories(): LiveData<List<Category>>
    suspend fun updateCategory(category: Category)


}