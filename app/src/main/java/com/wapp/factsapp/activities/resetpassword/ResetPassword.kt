package com.wapp.factsapp.activities.resetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.wapp.factsapp.R
import com.wapp.factsapp.databinding.ActivityResetPasswordBinding
import com.wapp.factsapp.utils.VALID_EMAIL
import com.wapp.factsapp.utils.displayMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPassword : AppCompatActivity() {

    val viewModel: ResetViewModel by viewModels()
    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_reset_password)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val auth = FirebaseAuth.getInstance()
        val email = binding.resetEmailEdit.text

        viewModel.checkValidity.observe(this,{
            when(it){
                VALID_EMAIL ->  auth.sendPasswordResetEmail(email.toString()).addOnCompleteListener{ x->
                    if(x.isSuccessful)
                        displayMessage(binding.resetActivityLayout,getString(R.string.check_reset))
                    else
                        displayMessage(binding.resetActivityLayout,getString(R.string.fail_reset_link))
                }
                else -> displayMessage(binding.resetActivityLayout,getString(R.string.invalid_email))
            }

        })

        viewModel.resetClick.observe(this,{
            if(it){
                viewModel.checkEmailValidity(email.toString())
                viewModel.onResetClicked()
            }
        })

        viewModel.backClick.observe(this,{
            if(it){
                onBackPressed()
                viewModel.onBackClicked()
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}