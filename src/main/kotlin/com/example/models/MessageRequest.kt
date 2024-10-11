package com.example.models

import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification

data class MessageRequest(
    val receiver: String,
    val message: Message
) {
    data class Message(
        val title: String,
        val body: String
    )
}

fun MessageRequest.toFirebaseMessage(): Message {
    return Message.builder()
        .setNotification(
            Notification.builder()
                .setTitle(message.title)
                .setBody(message.body)
                .build()
        ).setToken(receiver)
        .build()
}

fun testMessage(): MessageRequest {
    return MessageRequest(
        receiver = "<TOKEN>",
        message = MessageRequest.Message(
            title = "This is a test",
            "You are are receiving a test message from the ktor server"
        )
    )
}
