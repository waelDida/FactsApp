package com.wapp.factsapp.activities.init

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.wapp.factsapp.R
import com.wapp.factsapp.flow.MainActivity
import com.wapp.factsapp.utils.UPDATE_FLAG
import com.wapp.factsapp.utils.UPDATE_FLAG_VALUE
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InitActivity : AppCompatActivity() {

    val viewModel: InitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        supportActionBar?.hide()
        viewModel.updateFinished.observe(this,{
            when(it){
                InitViewModel.WITHOUT_UPDATES  -> {
                    startActivity(Intent(this@InitActivity,MainActivity::class.java))
                    finish()
                }
                InitViewModel.WITH_UPDATES -> {
                    val intent = Intent(this@InitActivity,MainActivity::class.java)
                    intent.putExtra(UPDATE_FLAG, UPDATE_FLAG_VALUE)
                    startActivity(intent)
                    finish()
                }
            }
        })
    }

    override fun onBackPressed() {}
}