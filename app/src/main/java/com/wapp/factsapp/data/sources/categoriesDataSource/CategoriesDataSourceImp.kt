package com.wapp.factsapp.data.sources.categoriesDataSource

import androidx.lifecycle.LiveData
import com.wapp.factsapp.models.Category
import com.wapp.factsapp.room.AppDatabase
import javax.inject.Inject

class CategoriesDataSourceImp @Inject constructor(private val appDatabase: AppDatabase): CategoriesDataSource {
    override suspend fun insertAllCategories(categories:List<Category>) {
        appDatabase.categoryDao.insertAll(categories)
    }

    override suspend fun getCategories(): List<Category> {
        return appDatabase.categoryDao.getCategories()
    }

    override fun getAllCategories(): LiveData<List<Category>> {
        return appDatabase.categoryDao.getAllCategories()
    }

    override suspend fun updateCategory(category: Category) {
        appDatabase.categoryDao.updateCategory(category)
    }
}