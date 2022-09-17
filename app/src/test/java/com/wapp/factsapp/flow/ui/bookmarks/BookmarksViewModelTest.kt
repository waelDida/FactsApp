package com.wapp.factsapp.flow.ui.bookmarks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.sources.fact.FakeFactRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BookmarksViewModelTest{
    private lateinit var bookmarksViewModel: BookmarksViewModel
    private lateinit var fakeFactRepository: FakeFactRepository
    private val fact1 = Fact("1",isBookmarked = true)
    private val fact2 = Fact("2",isBookmarked = true)
    private val fact3 = Fact("3",isBookmarked = false)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createViewModel(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        fakeFactRepository = FakeFactRepository()
        fakeFactRepository.addFacts(fact1,fact2,fact3)
        bookmarksViewModel = BookmarksViewModel(fakeFactRepository)
    }

    @Test
    fun `get the bookmarked facts`(){
        val x = bookmarksViewModel.fetchedData.getOrAwaitValue() as Resource.Success
        assertThat(x.data.size,IsEqual(2))
    }
}