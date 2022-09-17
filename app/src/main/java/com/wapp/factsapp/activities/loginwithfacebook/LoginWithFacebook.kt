package com.wapp.factsapp.activities.loginwithfacebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.wapp.factsapp.R
import com.wapp.factsapp.activities.init.InitActivity
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.signin.SignIn
import com.wapp.factsapp.utils.FACEBOOK_SIGN_SUCCEED
import com.wapp.factsapp.utils.INDICE_AFTER_CREATION
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import javax.inject.Inject

@AndroidEntryPoint
class LoginWithFacebook : AppCompatActivity() {

    val viewModel : LoginWithFacebookViewModel by viewModels()

    private lateinit var callbackManager: CallbackManager
    @Inject lateinit var userDataRepo: UserDataRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_with_facebook)

        supportActionBar?.hide()

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email","public_profile"))
        LoginManager.getInstance().registerCallback(callbackManager,object:
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                val bundle = Bundle()
                bundle.putString("fields","id,email,first_name")
                val request = GraphRequest.newMeRequest(result!!.accessToken){ _, _ ->
                    try{
                        handleFacebookAccessToken(result.accessToken)
                    }catch (e: JSONException){
                        e.printStackTrace()
                    }
                }
                request.parameters = bundle
                request.executeAsync()
            }

            override fun onCancel() {
                returnToSignInActivity()
                finish()
            }

            override fun onError(error: FacebookException?) {
                returnToSignInActivity()
                finish()
            }

        })

        viewModel.signInResult.observe(this,{
            when(it){
                FACEBOOK_SIGN_SUCCEED -> startInitActivity()
                else -> returnToSignInActivity()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken){
        val credential = FacebookAuthProvider.getCredential(token.token)
        AccessToken.setCurrentAccessToken(token)
        viewModel.signInWithFacebook(credential)
    }

    private fun startInitActivity(){
        startActivity(Intent(this@LoginWithFacebook, InitActivity::class.java))
        finish()
    }

    private fun returnToSignInActivity(){
        val intent = Intent(this@LoginWithFacebook, SignIn::class.java)
        intent.putExtra(INDICE_AFTER_CREATION,3)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {}
}