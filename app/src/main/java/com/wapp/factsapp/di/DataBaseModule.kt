package com.wapp.factsapp.di

import android.content.Context
import androidx.room.Room
import com.wapp.factsapp.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context,
            AppDatabase::class.java,
            AppDatabase.database_name).fallbackToDestructiveMigration().build()
    }

}