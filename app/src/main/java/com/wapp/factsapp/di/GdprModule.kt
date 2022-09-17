package com.wapp.factsapp.di


import com.wapp.factsapp.data.providers.gdpr.GdprProvider
import com.wapp.factsapp.data.providers.gdpr.GdprProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@InstallIn(ApplicationComponent::class)
@Module
abstract class GdprModule {

    @Binds
    abstract fun provideGdpr(imp: GdprProviderImpl): GdprProvider
}