package com.wapp.factsapp.activities.resetpassword

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.getOrAwaitValue
import com.wapp.factsapp.utils.INVALID_EMAIL
import com.wapp.factsapp.utils.VALID_EMAIL
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ResetViewModelTest{
    private lateinit var resetViewModel: ResetViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createViewModel(){
        resetViewModel = ResetViewModel()
    }

    @Test
    fun `verify that the email is valid`(){
        resetViewModel.checkEmailValidity("wael@hotmail.fr")
        assertThat(resetViewModel.checkValidity.getOrAwaitValue(),IsEqual(VALID_EMAIL))
    }

    @Test
    fun `verify that the email is invalid`(){
        resetViewModel.checkEmailValidity("hgfrfd@jhgdt")
        assertThat(resetViewModel.checkValidity.getOrAwaitValue(),IsEqual(INVALID_EMAIL))
    }
}