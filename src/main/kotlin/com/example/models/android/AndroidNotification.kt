package com.example.models.android

import java.time.Instant

data class Notification(
    val title: String,
    val body: String,
    val icon: String,
    val color: String,
    val sound: String,
    val tag: String,
    val clickAction: String,
    val bodyLocKey: String,
    val bodyLocArgs: List<String>,
    val titleLocKey: String,
    val titleLocArgs: List<String>,
    val channelId: String,
    val ticker: String,
    val sticky: Boolean,
    val eventTime: String,
    val localOnly: Boolean,
    val notificationPriority: NotificationPriority,
    val defaultSound: Boolean,
    val defaultVibrateTimings: Boolean,
    val defaultLightSettings: Boolean,
    val vibrateTimings: List<String>,
    val visibility: Visibility,
    val notificationCount: Int,
    val lightSettings: LightSettings,
    val image: String,
    val bypassProxyNotification: Boolean,
    val proxy: Proxy
)

val testNotification = Notification(
    title = "Test Notification",
    body = "This is a test notification with a long body.",
    icon = "notification_icon",
    color = "#FF0000",
    sound = "default",
    tag = "test_tag",
    clickAction = "com.example.app.MainActivity",
    bodyLocKey = "notification_body",
    bodyLocArgs = listOf("arg1", "arg2"),
    titleLocKey = "notification_title",
    titleLocArgs = listOf("arg3"),
    channelId = "test_channel",
    ticker = "Test Notification",
    sticky = true,
    eventTime = Instant.now().toString(),
    localOnly = false,
    notificationPriority = NotificationPriority.PRIORITY_HIGH,
    defaultSound = false,
    defaultVibrateTimings = false,
    defaultLightSettings = false,
    vibrateTimings = listOf("100", "200", "300"),
    visibility = Visibility.VISIBILITY_PUBLIC,
    notificationCount = 2,
    lightSettings = LightSettings(
        color = Color(red = 1f, green = 0f, blue = 0f),
        lightOnDuration = "1000",
        lightOffDuration = "1000"
    ),
    image = "notification_image.png",
    bypassProxyNotification = false,
    proxy = Proxy.ALLOW
)