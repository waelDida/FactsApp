package com.wapp.factsapp.activities.createwithemail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.sources.user.FakeUserRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.utils.REGISTERED_SUCCESSFULLY
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
class UserWithEmailViewModelTest{

    private lateinit var userWithEmailViewModel: UserWithEmailViewModel
    private lateinit var fakeUserDataRepo: FakeUserRepository
    private lateinit var fakeFireBaseProvider: FakeFireBaseProvider

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initViewModel(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        fakeFireBaseProvider = FakeFireBaseProvider()
        fakeFireBaseProvider.setId("1")
        fakeFireBaseProvider.setMessageToken("5236Test")
        fakeFireBaseProvider.setSignUpIndex(1)
        fakeUserDataRepo = FakeUserRepository()
        userWithEmailViewModel = UserWithEmailViewModel(fakeUserDataRepo,fakeFireBaseProvider)
        userWithEmailViewModel.createNewUser("Eric","test","test")
    }

    @Test
    fun `verify that the creation process works with success`() = runBlockingTest{
        assertThat(userWithEmailViewModel.creationCompleted.getOrAwaitValue(),IsEqual(REGISTERED_SUCCESSFULLY))
    }
}