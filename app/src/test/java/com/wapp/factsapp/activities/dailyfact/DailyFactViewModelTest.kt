package com.wapp.factsapp.activities.dailyfact

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.providers.preference.FakePrefProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.sources.fact.FakeFactRepository
import com.wapp.factsapp.data.sources.user.FakeUserRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.models.User
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
class DailyFactViewModelTest{
    private lateinit var dailyFactViewModel: DailyFactViewModel
    private lateinit var fakeFactRepository: FakeFactRepository
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var fakeFireBaseProvider: FakeFireBaseProvider
    private lateinit var preferenceProvider: PreferenceProvider
    private val fact1 = Fact("1",shortDescription = "fact1 short desc")
    private val fact2 = Fact("2",shortDescription = "fact2 short desc")
    private val facts = listOf(fact1,fact2)
    private val user = User("1",factNum = 3)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initViewModel() = runBlockingTest{
        Dispatchers.setMain(Dispatchers.Unconfined)
        fakeFactRepository = FakeFactRepository()
        fakeFactRepository.insertAllFactsIntoRoom(facts)

        fakeUserRepository = FakeUserRepository()
        fakeUserRepository.insertUserIntoRoom(user)

        fakeFireBaseProvider = FakeFireBaseProvider()
        fakeFireBaseProvider.setId("1")

        preferenceProvider = FakePrefProvider()

        dailyFactViewModel = DailyFactViewModel(fakeUserRepository,fakeFactRepository,fakeFireBaseProvider,preferenceProvider)
    }

    @Test
    fun`verify that the user will get the right fact`(){
        assertThat(dailyFactViewModel.dailyFact.getOrAwaitValue(),IsEqual(fact2))
    }
}