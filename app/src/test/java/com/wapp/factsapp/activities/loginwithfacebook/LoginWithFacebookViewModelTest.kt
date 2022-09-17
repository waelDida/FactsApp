package com.wapp.factsapp.activities.loginwithfacebook

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.sources.user.FakeUserRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.utils.FACEBOOK_SIGN_SUCCEED
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
class LoginWithFacebookViewModelTest{
    private lateinit var loginWithFacebookViewModel: LoginWithFacebookViewModel
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var fakeFireBaseProvider: FakeFireBaseProvider

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initViewModel(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        fakeFireBaseProvider = FakeFireBaseProvider()
        fakeFireBaseProvider.setId("1")
        fakeFireBaseProvider.setName("Eric")
        fakeFireBaseProvider.setPhotoUrl("url test")
        fakeFireBaseProvider.setMessageToken("token test")
        fakeFireBaseProvider.setFacebookSignInIndex(1)
        fakeUserRepository = FakeUserRepository()
        fakeUserRepository.setUserExistInFireStore(false)
        loginWithFacebookViewModel = LoginWithFacebookViewModel(fakeUserRepository,fakeFireBaseProvider)
        loginWithFacebookViewModel.signInWithFacebook(null)
    }

    @Test
    fun `verify that login with facebook pass with success`() = runBlockingTest{
        assertThat(loginWithFacebookViewModel.signInResult.getOrAwaitValue(),IsEqual(FACEBOOK_SIGN_SUCCEED))
    }

}