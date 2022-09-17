package com.wapp.factsapp.flow.ui.factsdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.wapp.factsapp.data.sources.fact.FakeFactRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.models.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FactsDetailsViewModelTest{

    private lateinit var factsDetailsViewModel: FactsDetailsViewModel
    private lateinit var fakeFactRepository: FakeFactRepository
    private lateinit var savedStateHandle: SavedStateHandle
    private val fact = Fact("1",isLiked = false,isBookmarked = false)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createViewModel()= runBlockingTest{
        Dispatchers.setMain(Dispatchers.Unconfined)
        savedStateHandle = SavedStateHandle()
        savedStateHandle.set("fact_id",fact.id)
        fakeFactRepository = FakeFactRepository()
        fakeFactRepository.insertFactIntoRoom(fact)
        factsDetailsViewModel = FactsDetailsViewModel(savedStateHandle,fakeFactRepository)
    }

    @Test
    fun `get the corresponding fact`(){
        assertThat(factsDetailsViewModel.currentFact.getOrAwaitValue(),IsEqual(fact))
    }

    @Test
    fun `test the like click`(){
        factsDetailsViewModel.onLikeDislike()
        assertThat(factsDetailsViewModel.currentFact.getOrAwaitValue().isLiked,IsEqual(true))
        factsDetailsViewModel.onLikeDislike()
        assertThat(factsDetailsViewModel.currentFact.getOrAwaitValue().isLiked,IsEqual(false))
    }

    @Test
    fun `the fact has been updated successfully in room after like click`(){
        factsDetailsViewModel.onLikeDislike()
        val x = fakeFactRepository.getLocalFact("1")
        assertThat(x.getOrAwaitValue().isLiked,IsEqual(true))
    }

    @Test
    fun`test the bookMark click`(){
        factsDetailsViewModel.onBookMarkDisBookMark()
        assertThat(factsDetailsViewModel.currentFact.getOrAwaitValue().isBookmarked,IsEqual(true))
        factsDetailsViewModel.onBookMarkDisBookMark()
        assertThat(factsDetailsViewModel.currentFact.getOrAwaitValue().isBookmarked,IsEqual(false))
    }

    @Test
    fun `the fact has been updated successfully in room after bookmark click`(){
        factsDetailsViewModel.onBookMarkDisBookMark()
        val y = fakeFactRepository.getLocalFact("1")
        assertThat(y.getOrAwaitValue().isBookmarked,IsEqual(true))
    }

    @Test
    fun `test the share click`(){
        factsDetailsViewModel.onShare()
        assertThat(factsDetailsViewModel.shareFact.getOrAwaitValue(),IsEqual(factsDetailsViewModel.currentFact.getOrAwaitValue()))
    }
}