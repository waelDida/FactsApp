package com.wapp.factsapp.flow.ui.categories.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.providers.preference.FakePrefProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.sources.categories.FakeCategoriesRepo
import com.wapp.factsapp.data.sources.user.FakeUserRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.utils.categoriesList
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CategoriesViewModelTest{
    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var fakeFireBaseProvider: FakeFireBaseProvider
    private lateinit var fakeCategoriesDataRepo: FakeCategoriesRepo
    private lateinit var fakePreferenceProvider: PreferenceProvider

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createViewModel(){
        fakeUserRepository = FakeUserRepository()
        fakeFireBaseProvider = FakeFireBaseProvider()
        fakeCategoriesDataRepo = FakeCategoriesRepo()
        fakePreferenceProvider = FakePrefProvider()

        categoriesViewModel = CategoriesViewModel(fakeUserRepository,fakeFireBaseProvider,fakeCategoriesDataRepo,fakePreferenceProvider)
    }

    @Test
    fun `get the categories list`(){
        assertThat(categoriesViewModel.categories.getOrAwaitValue(),IsEqual(categoriesList()))
    }
}