package com.wapp.factsapp.di

import android.app.NotificationManager
import android.content.Context
import androidx.core.content.ContextCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class NotificationServiceModule {

    @Provides
    @Singleton
    fun provideNotificationManager(@ApplicationContext context: Context) =
       ContextCompat.getSystemService(context,NotificationManager::class.java) as NotificationManager
}