package com.wapp.factsapp.flow.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.providers.preference.FakePrefProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.sources.fact.FakeFactRepository
import com.wapp.factsapp.data.sources.user.FakeUserRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.utils.categoriesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest{

    //class under test
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var fakeFactRepository: FakeFactRepository
    private lateinit var fakePrefProvider: FakePrefProvider
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var fakeFireBaseProvider: FakeFireBaseProvider

    private val fact1 = Fact("1",category = categoriesList()[0].name,isLiked = true) //category:Sport
    private val fact2 = Fact("2",category = categoriesList()[0].name) //category:Sport
    private val fact3 = Fact("3",category = categoriesList()[1].name) //category:Brain
    private val fact4 = Fact("4",category = categoriesList()[2].name) //category:WW2
    private val fact5 = Fact("5",category = categoriesList()[2].name)
    private val fact6 = Fact("6",category = categoriesList()[0].name,isBookmarked = true) //category:Sport
    private val fact7 = Fact("7",category = categoriesList()[3].name)
    private val fact8 = Fact("8",category = categoriesList()[0].name)
    private val fact9 = Fact("9",category = categoriesList()[0].name)
    private val fact10 = Fact("10",category = categoriesList()[0].name)

    private val pickedCategories = mutableListOf("WW2","Brain","Sport")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createViewModel(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        fakeFactRepository = FakeFactRepository()
       // fakeFactRepository.addFacts(fact1,fact2,fact3,fact4,fact5,fact6,fact7,fact8,fact9,fact10)
        fakeFactRepository.addFacts(fact1,fact2,fact3,fact4)

        fakePrefProvider = FakePrefProvider()
        fakePrefProvider.setPickedCategories(pickedCategories.toSet())

        fakeUserRepository = FakeUserRepository()
        fakeFireBaseProvider = FakeFireBaseProvider()

        homeViewModel = HomeViewModel(fakeFactRepository,fakePrefProvider,fakeUserRepository,fakeFireBaseProvider)
    }

    @Test
    fun `get facts by categories`() {
        val x = homeViewModel.facts.getOrAwaitValue()
        assertThat(x.size,IsEqual(3))
        assertThat(x.contains(fact6),IsEqual(false))
        assertThat(x.contains(fact1),IsEqual(false))
        assertThat(x.contains(fact7),IsEqual(false))
    }

}