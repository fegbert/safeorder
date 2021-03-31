package com.groupthree.safeorder.firebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.groupthree.safeorder.MainActivity
import com.groupthree.safeorder.R

class MyFirebaseCloudMessaging : FirebaseMessagingService(){

    private val tag = "FireBaseMessagingService"
    private var notificationChannelId = "com.groupthree.safeorder"
    private val notificationId = 100


    override fun onMessageReceived(msg: RemoteMessage) {
        super.onMessageReceived(msg)
        Log.e("message", "Message received.")

        if (msg.data.isNotEmpty()) {
            val title = msg.data["title"]
            val body = msg.data["body"]
            showNotification(applicationContext, title, body)
        } else {
            val title = msg.notification!!.title
            val body = msg.notification!!.body
            showNotification(applicationContext, title, body)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("token", "New token.")
    }

    private fun showNotification(context: Context, title: String?, message: String?) {

        val intent = Intent(context, MainActivity::class.java)
        intent.data = Uri.parse("custom://" + System.currentTimeMillis())
        intent.action = "actionstring" + System.currentTimeMillis()
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP

        val pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification : Notification

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = NotificationCompat.Builder(context, notificationChannelId)
                    .setOngoing(true)
                    .setSmallIcon(getNotificationIcon())
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pi)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .setWhen(System.currentTimeMillis())
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentTitle(title).build()

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notificationChannel = NotificationChannel(
                    notificationChannelId,
                    title,
                    NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManager.createNotificationChannel(notificationChannel)
            notificationManager.notify(notificationId, notification)

        } else {
            notification = NotificationCompat.Builder(context)
                    .setSmallIcon(getNotificationIcon())
                    .setAutoCancel(true)
                    .setContentText(message)
                    .setContentIntent(pi)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentTitle(title).build()
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(notificationId, notification)
        }
    }

    private fun getNotificationIcon(): Int {
        return R.mipmap.ic_launcher
    }
}