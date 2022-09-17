package com.wapp.factsapp.data.repositories.categoriesDataRepository

import androidx.lifecycle.LiveData
import com.wapp.factsapp.models.Category

interface CategoriesDataRepo {
    suspend fun insertAllCategories(categories: List<Category>)
    suspend fun getCategories():List<Category>
    fun getAllCategories(): LiveData<List<Category>>
    suspend fun updateCategory(category: Category)
}