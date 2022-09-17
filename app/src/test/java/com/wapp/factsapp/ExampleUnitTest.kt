package com.wapp.factsapp

import com.wapp.factsapp.utils.getFirstName
import com.wapp.factsapp.utils.isValidEmail
import org.hamcrest.core.IsEqual
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testEmail(){
        assertThat(isValidEmail("wael@hotmail.fr"),IsEqual(true))
    }

    @Test
    fun testGetFirstName(){
        val fullName = "Wael Saadaoui"
        assertThat(fullName.getFirstName(),IsEqual("Wael"))
    }
}