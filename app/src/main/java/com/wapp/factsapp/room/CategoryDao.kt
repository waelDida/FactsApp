package com.wapp.factsapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wapp.factsapp.models.Category

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories:List<Category>)

    @Query("SELECT * FROM Category")
    suspend fun getCategories(): List<Category>

    @Query("SELECT * FROM Category")
    fun getAllCategories(): LiveData<List<Category>>

    @Update
    suspend fun updateCategory(category: Category)
}