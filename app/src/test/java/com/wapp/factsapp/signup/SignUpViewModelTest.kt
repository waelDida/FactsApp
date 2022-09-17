package com.wapp.factsapp.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.utils.*
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpViewModelTest{

    private lateinit var signUpViewModel: SignUpViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createTheViewModel(){
        signUpViewModel = SignUpViewModel()
    }

    @Test
    fun `verify that some field are missed`(){
        signUpViewModel.getRegistrationInfo("","test","","")
        assertThat(signUpViewModel.checkValidity.getOrAwaitValue(),IsEqual(FILL_ALL_FIELDS))
    }

    @Test
    fun `verify that the password is not valid`(){
        signUpViewModel.getRegistrationInfo("wael","wael@hotmail.fr","1255","1255")
        assertThat(signUpViewModel.checkValidity.getOrAwaitValue(),IsEqual(INVALID_PASSWORD))
    }

    @Test
    fun `verify that the retyped password is wrong`(){
        signUpViewModel.getRegistrationInfo("wael","wael@hotmail.fr","didadida","didaudia")
        assertThat(signUpViewModel.checkValidity.getOrAwaitValue(),IsEqual(CHECK_PASSWORD))
    }

    @Test
    fun `verify that the email is invalid`(){
        signUpViewModel.getRegistrationInfo("wael","blhgaa@jhg","didadida","didadida")
        assertThat(signUpViewModel.checkValidity.getOrAwaitValue(),IsEqual(INVALID_EMAIL))
    }

    @Test
    fun `verify that all fields are valid`(){
        signUpViewModel.getRegistrationInfo("wael","wael@hotmail.fr","didadida","didadida")
        assertThat(signUpViewModel.checkValidity.getOrAwaitValue(),IsEqual(VALID_INFO))
    }
}