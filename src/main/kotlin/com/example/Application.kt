package com.example

import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureRouting()

    val firebaseAuthConfig = this::class.java.classLoader.getResourceAsStream("ktor-firebase-auth.json")
    val firebaseOptions = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(firebaseAuthConfig))
        .build()

    FirebaseApp.initializeApp(firebaseOptions)
}
