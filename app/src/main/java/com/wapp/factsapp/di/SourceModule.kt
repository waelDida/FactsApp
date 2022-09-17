package com.wapp.factsapp.di


import com.wapp.factsapp.data.sources.categoriesDataSource.CategoriesDataSource
import com.wapp.factsapp.data.sources.categoriesDataSource.CategoriesDataSourceImp
import com.wapp.factsapp.data.sources.factsDataSource.FactsDataSource
import com.wapp.factsapp.data.sources.factsDataSource.FactsDataSourceImp
import com.wapp.factsapp.data.sources.questionsDataSource.QuestionDataSource
import com.wapp.factsapp.data.sources.questionsDataSource.QuestionDataSourceImp
import com.wapp.factsapp.data.sources.userDataSource.UserDataSource
import com.wapp.factsapp.data.sources.userDataSource.UserDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class SourceModule {

    @Binds
    abstract fun provideUserSource(impl: UserDataSourceImp): UserDataSource

    @Binds
    abstract fun provideFactSource(impl: FactsDataSourceImp): FactsDataSource

    @Binds
    abstract fun provideCategoriesSource(impl: CategoriesDataSourceImp):CategoriesDataSource

    @Binds
    abstract fun provideQuestionsSource(impl: QuestionDataSourceImp): QuestionDataSource

}