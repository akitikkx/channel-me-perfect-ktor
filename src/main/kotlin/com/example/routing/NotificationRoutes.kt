package com.example.routing

import com.example.models.android.NotificationChannels
import com.example.models.android.testMessage
import com.example.models.android.testAndroidNotification
import com.example.models.android.toFirebaseMessage
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.sendNotification() {
    route("/send-basic") {
        get {
            // TODO use call.receive

            FirebaseMessaging.getInstance().send(testMessage().toFirebaseMessage())

            call.respond(HttpStatusCode.OK)

        }
    }

    route("/send-android-data-message") {
        get {
            // TODO use call.receive

            val randomChannel = NotificationChannels.entries.toTypedArray().random()

            val message = Message.builder()
                .putData("title", randomChannel.title)
                .putData("body", randomChannel.body)
                .putData("icon", testAndroidNotification.icon)
                .putData("color", testAndroidNotification.color)
                .putData("sound", testAndroidNotification.sound)
                .putData("tag", testAndroidNotification.tag)
                .putData("click_action", testAndroidNotification.clickAction)
                .putData("body_loc_key", testAndroidNotification.bodyLocKey)
                .putData("body_loc_args", testAndroidNotification.bodyLocArgs.joinToString(","))
                .putData("title_loc_key", testAndroidNotification.titleLocKey)
                .putData("title_loc_args", testAndroidNotification.titleLocArgs.joinToString(","))
                .putData("channel_id", randomChannel.name)
                .putData("ticker", testAndroidNotification.ticker)
                .putData("sticky", testAndroidNotification.sticky.toString())
                .putData("event_time", testAndroidNotification.eventTime)
                .putData("local_only", testAndroidNotification.localOnly.toString())
                .putData("notification_priority", testAndroidNotification.notificationPriority.name)
                .putData("default_sound", testAndroidNotification.defaultSound.toString())
                .putData("default_vibrate_timings", testAndroidNotification.defaultVibrateTimings.toString())
                .putData("default_light_settings", testAndroidNotification.defaultLightSettings.toString())
                .putData("vibrate_timings", testAndroidNotification.vibrateTimings.joinToString(","))
                .putData("visibility", testAndroidNotification.visibility.name)
                .putData("notification_count", testAndroidNotification.notificationCount.toString())
                .putData("light_settings", testAndroidNotification.lightSettings.toString())
                .putData("image", testAndroidNotification.image)
                .putData("bypass_proxy_notification", testAndroidNotification.bypassProxyNotification.toString())
                .putData("proxy", testAndroidNotification.proxy.name)
                // TODO retrieve token from call.receive
                .setToken("")
                .build()

            FirebaseMessaging.getInstance().send(message)

            call.respond(HttpStatusCode.OK)

        }
    }
}