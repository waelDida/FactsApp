package com.wapp.factsapp.flow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.providers.preference.FakePrefProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.sources.user.FakeUserRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test



@ExperimentalCoroutinesApi
class MainViewModelTest{

    private lateinit var mainViewModel: MainViewModel
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var fakeFireBaseProvider: FakeFireBaseProvider
    private lateinit var preferenceProvider: PreferenceProvider
    private val user = User("1")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initViewModel() = runBlockingTest{
        fakeFireBaseProvider = FakeFireBaseProvider()
        fakeFireBaseProvider.setId("1")
        fakeUserRepository = FakeUserRepository()
        fakeUserRepository.insertUserIntoRoom(user)
        preferenceProvider = FakePrefProvider()
        mainViewModel = MainViewModel(fakeFireBaseProvider,fakeUserRepository,preferenceProvider)
    }

    @Test
    fun `insert a user in local room and get a liveData`() {
        assertThat(mainViewModel.currentUser!!.getOrAwaitValue(),IsEqual(user))
    }

}