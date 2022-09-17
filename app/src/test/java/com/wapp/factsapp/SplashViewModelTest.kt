package com.wapp.factsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.sources.user.FakeUserRepository
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
class SplashViewModelTest{
    private lateinit var splashViewModel: SplashViewModel
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var fakeFirebaseProvider: FakeFireBaseProvider

    private val user = User("1",emailVerified = false)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initViewModel() = runBlockingTest{
        Dispatchers.setMain(Dispatchers.Unconfined)
        fakeFirebaseProvider = FakeFireBaseProvider()
        fakeFirebaseProvider.setId("1")
        fakeFirebaseProvider.setUserLoggedInIndex(false)
        fakeUserRepository = FakeUserRepository()
        fakeUserRepository.createNewUserInFireStore(user)
        splashViewModel = SplashViewModel(fakeUserRepository,fakeFirebaseProvider)
    }

    @Test
    fun`verify that the user access`(){
        assertThat(splashViewModel.userLoginAccess.getOrAwaitValue(),IsEqual(false))
    }
}