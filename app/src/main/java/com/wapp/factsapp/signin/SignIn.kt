package com.wapp.factsapp.signin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.google.android.gms.ads.MobileAds
import com.wapp.factsapp.R
import com.wapp.factsapp.activities.init.InitActivity
import com.wapp.factsapp.activities.loginanonymously.LoginAnonymously
import com.wapp.factsapp.activities.loginwithemail.LoginWithEmail
import com.wapp.factsapp.activities.loginwithfacebook.LoginWithFacebook
import com.wapp.factsapp.activities.loginwithgoogle.LoginWithGoogle
import com.wapp.factsapp.activities.resetpassword.ResetPassword
import com.wapp.factsapp.databinding.ActivitySignInBinding
import com.wapp.factsapp.signup.SignUp
import com.wapp.factsapp.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignIn : AppCompatActivity() {

    private lateinit var layout: ConstraintLayout
    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: ActivitySignInBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        layout = binding.signInLayout

        viewModel.login.observe(this, {
            if(it){
                if(isNetworkAvailable(this))
                    viewModel.checkValidity(binding.emailEdit.text.toString(),binding.passwordEdit.text.toString())
                else
                    displayMessage(layout,getString(R.string.cant_connect))
            }
        })

        viewModel.facebookLogin.observe(this,{
            if(it){
                if(isNetworkAvailable(this)){
                    startActivity(Intent(this@SignIn, LoginWithFacebook::class.java))
                    finish()
                }
                else
                    displayMessage(layout,getString(R.string.cant_connect))

            }
        })

        viewModel.googleLogin.observe(this, {
            if(it){
                if(isNetworkAvailable(this)){
                    val intent = Intent(this@SignIn, LoginWithGoogle::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                    displayMessage(layout,getString(R.string.cant_connect))
            }
        })

        viewModel.anonymously.observe(this,{
            if(it){
                if(isNetworkAvailable(this)){
                    val intent = Intent(this@SignIn,LoginAnonymously::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                    displayMessage(layout,getString(R.string.cant_connect))
            }
        })

        viewModel.signUp.observe(this,{
            if(it){
                startActivity(Intent(this@SignIn, SignUp::class.java))
                viewModel.onSignUpClicked()
            }
        })

        viewModel.forgetPassword.observe(this,{
            if(it){
                startActivity(Intent(this@SignIn, ResetPassword::class.java))
                viewModel.onForgotPasswordClicked()
            }

        })

        viewModel.cred.observe(this, {
            when(it){
                EMPTY_EMAIL_PASSWORD -> displayMessage(layout,getString(R.string.enter_email_pass))
                INVALID_EMAIL -> displayMessage(layout,getString(R.string.invalid_email))
                INVALID_PASSWORD -> displayLongMessage(layout,getString(R.string.invalid_password))
                VALID_EMAIL_PASSWORD -> {
                    viewModel.setCredValueToNull()
                    login()
                }
            }
        })

        viewModel.userSavedLocally.observe(this,{
            if(it){
                viewModel.userSaved()
                startInitActivity()
            }

        })

        //Init Ads
        MobileAds.initialize(this) {}

        displayMessageAfterUserCreation()
    }

    private fun login(){
        val intent = Intent(this@SignIn, LoginWithEmail::class.java)
        intent.putExtra(EMAIL_TAG,binding.emailEdit.text.toString().trim())
        intent.putExtra(PASSWORD_TAG,binding.passwordEdit.text.toString().trim())
        startActivityForResult(intent, LOGIN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Email Login Response
        if(requestCode == LOGIN_REQUEST_CODE ){
            if(resultCode == Activity.RESULT_OK){
                when(data?.getStringExtra(SIGN_IN_RESULT)){
                    SUCCESS -> {
                        viewModel.saveUserLocally()
                    }
                    EMAIL_VERIFICATION_FAIL -> {
                        displayMessage(layout,getString(R.string.verify_email))
                    }
                    FAIL -> {
                        displayMessage(layout,getString(R.string.authentication_failed))
                    }
                }
            }
        }
    }

    private fun startInitActivity(){
        val intent = Intent(this@SignIn, InitActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun displayMessageAfterUserCreation(){
        when(intent.getIntExtra(INDICE_AFTER_CREATION,0)){
            1 -> displayLongMessage(layout,getString(R.string.registration_message))
            2 -> displayMessage(layout,getString(R.string.failed_to_send_email))
            3 -> displayMessage(layout,getString(R.string.failed_to_sign_in))
            4 -> displayMessage(layout,getString(R.string.delete_message))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}