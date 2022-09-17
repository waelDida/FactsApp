package com.wapp.factsapp.activities.loginwithemail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.wapp.factsapp.R
import com.wapp.factsapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginWithEmail : AppCompatActivity() {

    val viewModel: LoginWithEmailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_with_email)

        supportActionBar?.hide()

        val email = intent.getStringExtra(EMAIL_TAG)
        val password = intent.getStringExtra(PASSWORD_TAG)

        viewModel.signIn(email!!,password!!)

        viewModel.signInResult.observe(this,{
            when(it){
                EMAIL_VERIFIED -> emailVerified()
                EMAIL_NOT_VERIFIED -> emailNotVerified()
                else -> signInFailed()
            }
        })
    }

    private fun emailVerified(){
        val returnIntent = Intent()
        returnIntent.putExtra(SIGN_IN_RESULT, SUCCESS)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }

    private fun emailNotVerified(){
        val returnIntent = Intent()
        returnIntent.putExtra(SIGN_IN_RESULT, EMAIL_VERIFICATION_FAIL)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }

    private fun signInFailed(){
        val returnIntent = Intent()
        returnIntent.putExtra(SIGN_IN_RESULT, FAIL)
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }
}