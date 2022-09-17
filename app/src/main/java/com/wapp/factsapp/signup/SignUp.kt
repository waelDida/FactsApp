package com.wapp.factsapp.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.wapp.factsapp.R
import com.wapp.factsapp.activities.createwithemail.CreateUserWithEmail
import com.wapp.factsapp.databinding.ActivitySignUpBinding
import com.wapp.factsapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUp : AppCompatActivity() {

    private lateinit var layout: ConstraintLayout
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        binding.lifecycleOwner = this

        layout = binding.signUpLayout
        binding.viewModel = viewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.signUp.observe(this, {
            if(it){
                if(isNetworkAvailable(this))
                    viewModel.getRegistrationInfo(
                            binding.nameEditText.text.toString().trim(),
                            binding.emailEditText.text.toString(),
                            binding.passwordEditText.text.toString(),
                            binding.confirmPass.text.toString())
                else
                    displayMessage(layout,getString(R.string.cant_connect))
            }
        })

        viewModel.checkValidity.observe(this, {
            when(it){
                FILL_ALL_FIELDS -> displayMessage(layout,getString(R.string.fill_all_fields))
                INVALID_PASSWORD -> displayMessage(layout,getString(R.string.invalid_password))
                CHECK_PASSWORD -> displayMessage(layout,getString(R.string.check_password))
                INVALID_EMAIL -> displayMessage(layout,getString(R.string.invalid_email))
                VALID_INFO -> {
                    if(isNetworkAvailable(this))
                        createNewUser()
                    else
                        displayMessage(layout,getString(R.string.cant_connect))
                }
            }
        })

        viewModel.backClick.observe(this,{
            if(it){
                viewModel.onBackClicked()
                onBackPressed()
            }
        })
    }

    private fun createNewUser(){
        val intent = Intent(this@SignUp, CreateUserWithEmail::class.java)
        intent.putExtra(USER_NAME_TAG, binding.nameEditText.text.toString().trim())
        intent.putExtra(EMAIL_TAG,binding.emailEditText.text.toString())
        intent.putExtra(PASSWORD_TAG,binding.passwordEditText.text.toString())
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}