package com.wapp.factsapp.activities.loginanonymously

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.wapp.factsapp.R
import com.wapp.factsapp.activities.init.InitActivity
import com.wapp.factsapp.signin.SignIn
import com.wapp.factsapp.utils.ANONYMOUSLY_SIGN_SUCCEED
import com.wapp.factsapp.utils.INDICE_AFTER_CREATION
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginAnonymously : AppCompatActivity() {
    val viewModel:LoginAnonymouslyVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_anonymously)
        supportActionBar?.hide()

        viewModel.signInResult.observe(this,{
            when(it){
                ANONYMOUSLY_SIGN_SUCCEED -> startInitActivity()
                else -> returnToSignInActivity()
            }
        })
    }

    private fun startInitActivity(){
        startActivity(Intent(this@LoginAnonymously, InitActivity::class.java))
        finish()
    }

    private fun returnToSignInActivity(){
        val intent = Intent(this@LoginAnonymously, SignIn::class.java)
        intent.putExtra(INDICE_AFTER_CREATION,3)
        startActivity(intent)
        finish()
    }


    override fun onBackPressed() {}
}