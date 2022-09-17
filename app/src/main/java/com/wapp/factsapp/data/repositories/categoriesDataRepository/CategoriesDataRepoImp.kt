package com.wapp.factsapp.data.repositories.categoriesDataRepository

import androidx.lifecycle.LiveData
import com.wapp.factsapp.data.sources.categoriesDataSource.CategoriesDataSource
import com.wapp.factsapp.models.Category
import javax.inject.Inject

class CategoriesDataRepoImp @Inject constructor(private val categoriesDataSource: CategoriesDataSource): CategoriesDataRepo {
    override suspend fun insertAllCategories(categories: List<Category>) {
        categoriesDataSource.insertAllCategories(categories)
    }

    override suspend fun getCategories(): List<Category> {
        return categoriesDataSource.getCategories()
    }

    override fun getAllCategories(): LiveData<List<Category>> {
        return categoriesDataSource.getAllCategories()
    }

    override suspend fun updateCategory(category: Category) {
        categoriesDataSource.updateCategory(category)
    }
}