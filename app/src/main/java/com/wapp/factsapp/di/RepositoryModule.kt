package com.wapp.factsapp.di

import com.wapp.factsapp.data.repositories.categoriesDataRepository.CategoriesDataRepo
import com.wapp.factsapp.data.repositories.categoriesDataRepository.CategoriesDataRepoImp
import com.wapp.factsapp.data.repositories.factsDataRepository.FactsDataRepo
import com.wapp.factsapp.data.repositories.factsDataRepository.FactsDataRepoImp
import com.wapp.factsapp.data.repositories.questionsDataRepository.QuestionDataRepoImp
import com.wapp.factsapp.data.repositories.questionsDataRepository.QuestionDataRepository
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideUserRepository(impl: UserDataRepoImp): UserDataRepo

    @Binds
    abstract fun provideFactRepository(impl: FactsDataRepoImp): FactsDataRepo

    @Binds
    abstract fun provideCategoriesRepository(impl: CategoriesDataRepoImp): CategoriesDataRepo

    @Binds
    abstract fun provideQuestionsRepository(impl: QuestionDataRepoImp):QuestionDataRepository


}