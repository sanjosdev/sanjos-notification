package dev.sanjos.sanjos_notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import dev.sanjos.sanjos_notification.utility.NotifUtility
import java.lang.Exception

/** SanjosNotificationPlugin */
class SanjosNotificationPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {

    private lateinit var channel: MethodChannel
    private val channelID = "dev.sanjos.sanjos_notification"
    private var notificationManager: NotificationManager? = null
    private lateinit var context: Context
    private lateinit var activity: Activity
    private val notificationId = 7
    private val KEY_TEXT_REPLY = "SANTRI_NJOSO_DEVELOPER"

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, "sanjos_notification")
        channel.setMethodCallHandler(this)
        context = flutterPluginBinding.applicationContext
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "Reply Notif", "Example Reply Notif")
    }


    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "sanjos_notification")
            channel.setMethodCallHandler(SanjosNotificationPlugin())

        }


    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "showBasicNotification") {
            val title = call.argument<String?>("title")
            val content = call.argument<String?>("content")
            try {
                NotifUtility().sendBasicNotification(notificationManager, context, notificationId, channelID, title!!.toString(), content.toString())
            } catch (e: Exception) {
                io.flutter.Log.e("Notification", e.toString())
            }
        } else if (call.method == "showReplyNotification") {
            val title = call.argument<String?>("title")
            val content = call.argument<String?>("content")
            try {
                NotifUtility().sendReplyNotification(notificationManager, context, notificationId, "Enter Your Reply Here", "SANJOS_DEVELOPER", channelID, title!!.toString(), content.toString())
            } catch (e: Exception) {
                io.flutter.Log.e("Notification", e.toString())
            }
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    private fun createNotificationChannel(id: String, name: String, desc: String) {

        // TODO : Set IMPORTANCE from parameter
        val importance = NotificationManager.IMPORTANCE_HIGH

        var channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(id, name, importance).apply {
                description = desc
                enableLights(true)
                lightColor = Color.GREEN
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            }
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        notificationManager?.createNotificationChannel(channel)

    }

//    fun sendNotification() {
//        val replyLabel = "Enter Your Reply Here"
//        val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).setLabel(replyLabel).build()
//
//        val resultIntent = Intent(context, SanjosNotificationPlugin::class.java)
//        resultIntent.action = "REPLY_ACTION"
//        resultIntent.putExtra("KEY_NOTIFICATION_ID", notificationId)
//        resultIntent.putExtra("KEY_CHANNEL_ID", channelID)
//        resultIntent.putExtra("KEY_MESSAGE_ID", 2)
//        val resultPendingIntent =
//                PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val replyAction = NotificationCompat.Action.Builder(
//                android.R.drawable.ic_menu_call,
//                "Suku Notif",
//                resultPendingIntent
//        ).apply {
//            addRemoteInput(remoteInput)
//        }.build()
//
//        val newMessageNotification = NotificationCompat.Builder(context, channelID).apply {
//            setSmallIcon(android.R.drawable.alert_dark_frame)
//            setContentTitle("This Is Title")
//            setContentText("Hello you get notif from Sanjos")
//        }.build()
//
//
//        notificationManager?.notify(
//                notificationId,
//                newMessageNotification
//        )
//    }

    override fun onDetachedFromActivity() {
        TODO("Not yet implemented")
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

}
