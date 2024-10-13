package com.example.routing

import com.example.models.android.NotificationChannels
import com.example.models.android.testMessage
import com.example.models.android.testNotification
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
                .putData("icon", testNotification.icon)
                .putData("color", testNotification.color)
                .putData("sound", testNotification.sound)
                .putData("tag", testNotification.tag)
                .putData("click_action", testNotification.clickAction)
                .putData("body_loc_key", testNotification.bodyLocKey)
                .putData("body_loc_args", testNotification.bodyLocArgs.joinToString(","))
                .putData("title_loc_key", testNotification.titleLocKey)
                .putData("title_loc_args", testNotification.titleLocArgs.joinToString(","))
                .putData("channel_id", randomChannel.name)
                .putData("ticker", testNotification.ticker)
                .putData("sticky", testNotification.sticky.toString())
                .putData("event_time", testNotification.eventTime)
                .putData("local_only", testNotification.localOnly.toString())
                .putData("notification_priority", testNotification.notificationPriority.name)
                .putData("default_sound", testNotification.defaultSound.toString())
                .putData("default_vibrate_timings", testNotification.defaultVibrateTimings.toString())
                .putData("default_light_settings", testNotification.defaultLightSettings.toString())
                .putData("vibrate_timings", testNotification.vibrateTimings.joinToString(","))
                .putData("visibility", testNotification.visibility.name)
                .putData("notification_count", testNotification.notificationCount.toString())
                .putData("light_settings", testNotification.lightSettings.toString())
                .putData("image", testNotification.image)
                .putData("bypass_proxy_notification", testNotification.bypassProxyNotification.toString())
                .putData("proxy", testNotification.proxy.name)
                // TODO retrieve token from call.receive
                .setToken("")
                .build()

            FirebaseMessaging.getInstance().send(message)

            call.respond(HttpStatusCode.OK)

        }
    }
}