package com.wapp.factsapp.signin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.providers.firebase.FakeFireBaseProvider
import com.wapp.factsapp.data.sources.user.FakeUserRepository
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.models.User
import com.wapp.factsapp.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SignInViewModelTest{

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var fakeFireBaseProvider: FakeFireBaseProvider
    private val user = User("1")


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initViewModel() = runBlocking{
        Dispatchers.setMain(Dispatchers.Unconfined)
        fakeUserRepository = FakeUserRepository()
        fakeUserRepository.createNewUserInFireStore(user)
        fakeFireBaseProvider = FakeFireBaseProvider()
        fakeFireBaseProvider.setId("1")
        signInViewModel = SignInViewModel(fakeUserRepository,fakeFireBaseProvider)
    }

    @Test
    fun`verify if some field are missed`(){
        signInViewModel.checkValidity("","didadida")
        assertThat(signInViewModel.cred.getOrAwaitValue(),IsEqual(EMPTY_EMAIL_PASSWORD))
    }

    @Test
    fun `verify if the email is invalid`(){
        signInViewModel.checkValidity("wahe@khej","didadida")
        assertThat(signInViewModel.cred.getOrAwaitValue(),IsEqual(INVALID_EMAIL))
    }

    @Test
    fun `verify if the password is invalid`(){
        signInViewModel.checkValidity("wael@hotmail.fr","dida")
        assertThat(signInViewModel.cred.getOrAwaitValue(),IsEqual(INVALID_PASSWORD))
    }

    @Test
    fun `verify that all fields are valid`(){
        signInViewModel.checkValidity("wael_saadaoui@hotmail.fr","didadida85")
        assertThat(signInViewModel.cred.getOrAwaitValue(),IsEqual(VALID_EMAIL_PASSWORD))
    }

    @Test
    fun `verify that the user has been saved in local room database`(){
        signInViewModel.saveUserLocally()
        assertThat(signInViewModel.userSavedLocally.getOrAwaitValue(),IsEqual(true))
    }

}