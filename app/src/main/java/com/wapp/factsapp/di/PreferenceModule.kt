package com.wapp.factsapp.di

import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProviderImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class PreferenceModule {

    @Binds
    abstract fun provideAppPref(imp: PreferenceProviderImp):PreferenceProvider
}