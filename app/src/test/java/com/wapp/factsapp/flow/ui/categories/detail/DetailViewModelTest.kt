package com.wapp.factsapp.flow.ui.categories.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.wapp.factsapp.data.providers.preference.FakePrefProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.sources.fact.FakeFactRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.models.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest{
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var fakeFactRepository: FakeFactRepository
    private lateinit var preferenceProvider: PreferenceProvider
    private val fact1 = Fact("1",category = "sport")
    private val fact2 = Fact("2",category = "sport")
    private val fact3 = Fact("3",category = "sport")
    private val fact4 = Fact("4",category = "sport")
    private val fact5 = Fact("5",category = "sport")
    private val fact6 = Fact("6",category = "sport")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createViewModel(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        savedStateHandle = SavedStateHandle()
        savedStateHandle.set("category","sport")
        fakeFactRepository = FakeFactRepository()
        fakeFactRepository.addFacts(fact1,fact2,fact3,fact4,fact5,fact6)
        preferenceProvider = FakePrefProvider()
        detailViewModel = DetailViewModel(savedStateHandle,fakeFactRepository,preferenceProvider)
    }

    @Test
    fun `get facts by category`(){
        assertThat(detailViewModel.factsByCategory.getOrAwaitValue().size,IsEqual(6))
    }
}