package com.wapp.factsapp.di

import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.providers.firebase.FirebaseProviderImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class FirebaseModule {

    @Binds
    abstract fun provideFirebaseProvider(impl:FirebaseProviderImp):FirebaseProvider


}