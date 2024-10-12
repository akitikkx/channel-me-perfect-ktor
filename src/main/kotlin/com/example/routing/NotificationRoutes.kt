package com.example.routing

import com.example.models.MessageRequest
import com.example.models.testMessage
import com.example.models.toFirebaseMessage
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

    route("/send-with-channel") {
        get {
            // TODO use call.receive

            val message = Message.builder()
                .putData("title", "Test title")
                .putData("body", "Test notification body")
                .putData("channel_id", "promo_channel_id")
                .putData("channel_name", "Promo Channel")
                .putData("channel_description", "This is for promo notifications")
                // TODO retrieve token from call.receive
                .setToken("")
                .build()

            FirebaseMessaging.getInstance().send(message)

            call.respond(HttpStatusCode.OK)

        }
    }
}