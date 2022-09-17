package com.wapp.factsapp.activities.createwithemail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.wapp.factsapp.R
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.signin.SignIn
import com.wapp.factsapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateUserWithEmail : AppCompatActivity() {
    val viewModel: UserWithEmailViewModel by viewModels()

    @Inject
    lateinit var userDataRepo: UserDataRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_with_email)
        supportActionBar?.hide()

        val name = intent.getStringExtra(USER_NAME_TAG)
        val email = intent.getStringExtra(EMAIL_TAG)
        val password = intent.getStringExtra(PASSWORD_TAG)

        viewModel.createNewUser(name!!,email!!,password!!)

        viewModel.creationCompleted.observe(this,{
            when(it){
                REGISTERED_SUCCESSFULLY -> returnToSignInActivity(REGISTERED_SUCCESSFULLY)
                FAILED_SEND_VERIFICATION_EMAIL -> returnToSignInActivity(FAILED_SEND_VERIFICATION_EMAIL)
                else -> returnToSignInActivity(FAILED_TO_SIGN_IN)
            }
        })

    }

    private fun returnToSignInActivity(x: Int){
        val intent = Intent(this@CreateUserWithEmail, SignIn::class.java)
        intent.putExtra(INDICE_AFTER_CREATION,x)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {}
}