package com.example.routing

import com.example.models.MessageRequest
import com.example.models.testMessage
import com.example.models.toFirebaseMessage
import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.sendNotification() {
    route("/send") {
        get {
            // TODO use call.receive

            FirebaseMessaging.getInstance().send(testMessage().toFirebaseMessage())

            call.respond(HttpStatusCode.OK)

        }
    }
}