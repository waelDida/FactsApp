package com.wapp.factsapp.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.wapp.factsapp.R
import com.wapp.factsapp.activities.dailyfact.DailyFact

private const val NOTIFICATION_ID = 0
private const val REQUEST_CODE = 0

fun NotificationManager.sendNotification(title: String, message:String,context: Context){

    // Create the content intent for the notification
    val contentIntent = Intent(context,DailyFact::class.java)
    contentIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    val pendingContentIntent = PendingIntent.getActivity(
        context,
        REQUEST_CODE,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT)

    // Build the notification
    val builder = NotificationCompat.Builder(context,context.getString(R.string.fact_notification_channel_id))
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle(title)
        .setContentText(message)
        .setContentIntent(pendingContentIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    // Call the notification
    notify(NOTIFICATION_ID,builder.build())
}