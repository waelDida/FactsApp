package com.wapp.factsapp.activities.loginwithgoogle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.wapp.factsapp.R
import com.wapp.factsapp.activities.init.InitActivity
import com.wapp.factsapp.signin.SignIn
import com.wapp.factsapp.utils.GOOGLE_SIGN_SUCCEED
import com.wapp.factsapp.utils.GO_SIGN_IN
import com.wapp.factsapp.utils.INDICE_AFTER_CREATION
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginWithGoogle : AppCompatActivity() {

    val viewModel: LoginWithGoogleViewModel by viewModels()
    private lateinit var gso: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_with_google)

        // Configure Google Sign In
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        signInWithGoogle()

        viewModel.signInResult.observe(this, {
            when(it){
                GOOGLE_SIGN_SUCCEED -> startInitActivity()
                else -> returnToSignInActivity()
            }
        })
    }

    // Google Sign In
    private fun signInWithGoogle() {
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GO_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GO_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                viewModel.signInWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun startInitActivity(){
        startActivity(Intent(this@LoginWithGoogle, InitActivity::class.java))
        finish()
    }

    private fun returnToSignInActivity(){
        val intent = Intent(this@LoginWithGoogle, SignIn::class.java)
        intent.putExtra(INDICE_AFTER_CREATION,3)
        startActivity(intent)
        finish()
    }
}