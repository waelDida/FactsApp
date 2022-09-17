package com.wapp.factsapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.wapp.factsapp.activities.init.InitActivity
import com.wapp.factsapp.flow.MainActivity
import com.wapp.factsapp.signin.SignIn
import com.wapp.factsapp.utils.isNetworkAvailable
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    val viewModel: SplashViewModel by viewModels()
    @Inject lateinit var  notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        viewModel.userLoginAccess.observe(this,{
            if(it){
                if(isNetworkAvailable(this))
                    startInitActivity()
                else
                    startMainActivity()
            }
            else
                startSignInActivity()
            })

        // Create a channel for messages
        createNotificationChannel(
            application.getString(R.string.fact_notification_channel_id),
            getString(R.string.fact_notification_channel_name),
            notificationManager)

        // Create a channel for fcm
        createNotificationChannel(
            getString(R.string.fcm_notification_channel_id),
            getString(R.string.fcm_notification_channel_name),
            notificationManager
        )


    }

    private fun startMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun startInitActivity(){
        val intent = Intent(this@SplashActivity, InitActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startSignInActivity(){
        val intent = Intent(this@SplashActivity, SignIn::class.java)
        startActivity(intent)
        finish()
    }


    // Create a location channel
    private fun createNotificationChannel(channelId: String, channelName: String,notificationManager: NotificationManager){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }


}