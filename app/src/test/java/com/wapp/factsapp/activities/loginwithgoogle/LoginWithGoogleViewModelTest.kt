package com.wapp.factsapp.activities.loginwithgoogle

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.providers.preference.FakePrefProvider
import com.wapp.factsapp.data.providers.preference.PreferenceProvider
import com.wapp.factsapp.data.sources.user.FakeUserRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.utils.GOOGLE_SIGN_SUCCEED
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
class LoginWithGoogleViewModelTest{
    private lateinit var loginWithGoogleViewModel: LoginWithGoogleViewModel
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var fakeFirebaseProvider: FakeFireBaseProvider
    private lateinit var preferenceProvider: PreferenceProvider

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initViewModel(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        fakeFirebaseProvider = FakeFireBaseProvider()
        fakeFirebaseProvider.setId("1")
        fakeFirebaseProvider.setName("Eric")
        fakeFirebaseProvider.setPhotoUrl("test url")
        fakeFirebaseProvider.setMessageToken("tokenTest5252")
        fakeFirebaseProvider.setGoogleSignInIndex(1)
        fakeUserRepository = FakeUserRepository()
        fakeUserRepository.setUserExistInFireStore(false)
        preferenceProvider = FakePrefProvider()
        loginWithGoogleViewModel = LoginWithGoogleViewModel(fakeUserRepository,fakeFirebaseProvider,preferenceProvider)
        loginWithGoogleViewModel.signInWithGoogle("test")
    }

    @Test
    fun`verify that the sign in with google work with success`() = runBlockingTest{
        assertThat(loginWithGoogleViewModel.signInResult.getOrAwaitValue(),IsEqual(
            GOOGLE_SIGN_SUCCEED))

    }
}