package dev.sanjos.sanjos_notification.utility

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import java.lang.Exception
import java.util.*

class NotifUtility {

    // TODO : Make More Parameter For Costumizing the basic notification
    fun sendBasicNotification(notifcationManager: NotificationManager?, context: Context, notifId: Int, channelId: String, title: String, content: String) {

        val basicNotifcation = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(android.R.drawable.stat_notify_chat)
            setContentTitle(title)
            setContentText(content)
        }.build()

        notifcationManager?.notify(notifId, basicNotifcation)

    }

    fun sendReplyNotification(notifcationManager: NotificationManager?,context: Context, notifId: Int, replyLabel : String, keyTextReply : String, channelId: String, title: String, content: String) {

        val remoteInput = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            androidx.core.app.RemoteInput.Builder(keyTextReply)
                    .setLabel(replyLabel).build()
        } else {
            TODO("VERSION.SDK_INT < KITKAT_WATCH")
        }

        val intent = Intent(context, getMainActivity(context))
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val replpyAction = NotificationCompat.Action.Builder(android.R.drawable.stat_notify_chat, "Sanjos Notification", pendingIntent).apply {
            addRemoteInput(remoteInput)
        }.build()

        val replyNotification = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(android.R.drawable.stat_notify_chat)
            setContentTitle(title)
            setContentText(content)
            addAction(replpyAction)
        }.build()

        notifcationManager?.notify(notifId, replyNotification)

    }

    /// getMainActivity, is function to get MainActivity Class from client package
    fun getMainActivity(context: Context): Class<*> {

        val packageName = context.packageName
        val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
        val className = launchIntent.component.className

        try {
            return Class.forName(className)
        } catch (e: Exception) {
            throw e
        }

    }

}

