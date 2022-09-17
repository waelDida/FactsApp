package com.wapp.factsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wapp.factsapp.models.Category
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.models.Question
import com.wapp.factsapp.models.User

@Database(entities = [User::class,Fact::class,Category::class, Question::class],version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val factDao: FactDao
    abstract val userDao: UserDao
    abstract val categoryDao: CategoryDao
    abstract val questionDao: QuestionDao

    companion object{
        const val database_name = "app_data_base"
    }
}