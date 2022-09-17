package com.wapp.factsapp.activities.deleteaccount

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.wapp.factsapp.R
import com.wapp.factsapp.signin.SignIn
import com.wapp.factsapp.utils.INDICE_AFTER_CREATION
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAccount : AppCompatActivity() {

    val viewModel : DeleteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)

        viewModel.deleteCompleted.observe(this,{
            if(it){
                val intent = Intent(this@DeleteAccount,SignIn::class.java)
                intent.putExtra(INDICE_AFTER_CREATION,4)
                startActivity(intent)
                finish()
            }
            else{
                startActivity(Intent(this@DeleteAccount,SignIn::class.java))
            }
        })
    }

    override fun onBackPressed() {}
}