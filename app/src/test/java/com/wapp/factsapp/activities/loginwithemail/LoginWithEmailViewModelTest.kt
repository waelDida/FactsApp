package com.wapp.factsapp.activities.loginwithemail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.sources.user.FakeUserRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.models.User
import com.wapp.factsapp.utils.EMAIL_VERIFIED
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
class LoginWithEmailViewModelTest{

    private lateinit var loginWithEmailViewModel: LoginWithEmailViewModel
    private lateinit var fakeFirebaseProvider: FakeFireBaseProvider
    private lateinit var fakeUserRepository: FakeUserRepository

    private val user = User("1",emailVerified = false)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initViewModel() = runBlockingTest{
        Dispatchers.setMain(Dispatchers.Unconfined)
        fakeFirebaseProvider = FakeFireBaseProvider()
        fakeFirebaseProvider.setId("1")
        fakeFirebaseProvider.setSignInIndex(1)

        fakeUserRepository = FakeUserRepository()
        fakeUserRepository.createNewUserInFireStore(user)

        loginWithEmailViewModel = LoginWithEmailViewModel(fakeFirebaseProvider,fakeUserRepository)
        loginWithEmailViewModel.signIn("test","test")
    }

    @Test
    fun`verify that the user sign in successfully`() = runBlockingTest{
        assertThat(loginWithEmailViewModel.signInResult.getOrAwaitValue(),IsEqual(EMAIL_VERIFIED))
    }
}