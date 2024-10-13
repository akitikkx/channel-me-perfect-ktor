# Channel Me Perfect Ktor - A Ktor-based Firebase Admin SDK Sample Backend

## About the application

This is a sample Ktor project that aims to demonstrate using Firebase Admin SDK to send push notifications to specific 
Android devices that have the `com.google.firebase:firebase-messaging` SDK installed and actively listening for new 
messages. [See <a href="https://github.com/akitikkx/channel-me-perfect-android">here</a> a sample Android app I 
created to interact/react with the server]. The idea is to mimic common server interactions where a server manages 
certain user data and periodically sends each user a notification(s). This Ktor backend will act as that server and send 
notifications through Firebase, which are then transmitted and received by the target devices.

The sample currently sends push notifications to a single device, using a given Firebase Messaging token. 
The push notifications sent from this sample are currently of two message types as per the 
documentation 
[See the "Handling Messages" section of the Firebase <a href="https://firebase.google.com/docs/cloud-messaging/concept-options#notifications_and_data_messages">documentation</a>]  - notification messages and data messages:

> Notification messages, sometimes thought of as "display messages." These are handled by the FCM SDK automatically.

> Data messages, which are handled by the client app.

This sample app responds with one of these two message types [currently] through a `get` request to one of two endpoints:

- `/send-basic` - sends a basic notification message type consisting of a title and body. The Android device will 
handle this automatically through the Firebase Messaging SDK while the app is in the background;
- `/send-android-data-message` - sends a data type Android message following the JSON structure mentioned in the 
Firebase documentation <a href="https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#androidnotification">here</a>, which looks like the following: 

```
{
  "title": string,
  "body": string,
  "icon": string,
  "color": string,
  "sound": string,
  "tag": string,
  "click_action": string,
  "body_loc_key": string,
  "body_loc_args": [
    string
  ],
  "title_loc_key": string,
  "title_loc_args": [
    string
  ],
  "channel_id": string,
  "ticker": string,
  "sticky": boolean,
  "event_time": string,
  "local_only": boolean,
  "notification_priority": enum (NotificationPriority),
  "default_sound": boolean,
  "default_vibrate_timings": boolean,
  "default_light_settings": boolean,
  "vibrate_timings": [
    string
  ],
  "visibility": enum (Visibility),
  "notification_count": integer,
  "light_settings": {
    object (LightSettings)
  },
  "image": string,
  "bypass_proxy_notification": boolean,
  "proxy": enum (Proxy)
}
```
This type of message which contains only data (and no `com.google.firebase.messaging.Notification` information) will 
need to be handled by the Android client's `FirebaseMessagingService#onMessageReceived(message: RemoteMessage)` 
override [see the <a href="https://github.com/akitikkx/channel-me-perfect-android">sample 
Android app</a> for the example implementation].

## Setting up the project
The project requires that you get a private key file from Firebase. You will need an existing Firebase project and 
from there navigate to `Project Settings` > `Service Accounts` > `Firebase Admin SDK`. Choose `Java` for the 
"Admin SDK configuration snippet" then click "Generate new private key".

Store this downloaded file in the `src/main/resources` folder. The `src/main/kotlin/com/example/Application.kt` expects the filename to 
be `ktor-firebase-auth.json`. Feel free to name the file how you would want and remember to update the name inside the 
`getResourceAsStream()` call in `src/main/kotlin/com/example/Application.kt` as well as in the `.gitignore` file. The file is ignored in `.gitignore` and 
should remain that way as this is a private file that should not be committed to the repository.

## Dependencies
- Firebase Admin `com.google.firebase:firebase-admin`
- Content Negotiation `io.ktor:ktor-server-content-negotiation`
- Serialization `io.ktor:ktor-serialization-gson`

## Endpoints

The configurations for the endpoints `send-basic` and `send-android-data-message` are defined in 
`src/main/kotlin/com/example/routing/NotificationRoutes.kt`

### `send-android-data-message`

Imagine a server that manages user data as part of a service. Users can interact with the service through an app such 
as an Android app. Certain triggers such as user interactions, new marketing campaigns, reminders or alerts where 
users will need to be notified in the form of notifications. These notifications will need to be delivered to the users
who of course have subscribed to app notifications. 

The aim of this sample endpoint is to mimic that scenario where the server needs to send data in the form of a payload 
[see the JSON above] to their Android users. One of the attributes of note in the payload, is the `channel_id` which
according the <a href="https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#androidnotification">documentation</a>:

> channel_id: string

> The notification's channel id (new in Android O). The app must create a channel with this channel ID before any 
notification with this channel ID is received. If you don't send this channel ID in the request, or if the channel ID 
provided has not yet been created by the app, FCM uses the channel ID specified in the app manifest.

The configuration as in defined in `src/main/kotlin/com/example/routing/NotificationRoutes.kt` constructs a 
`com.google.firebase.messaging.Messaging` object particularly using the `putData` builder function to construct a data
message type. 

Messages from a server can fall into different categories (or channels). To replicate this, the enum 
class `src/main/kotlin/com/example/models/android/NotificationChannels.kt` has 10 random notification channels for 
popular Android notification channels for a TV show and movie app defined. Each value has a `title` and `body`. Then in
`src/main/kotlin/com/example/routing/NotificationRoutes.kt` a channel is picked at random using

```
val randomChannel = NotificationChannels.entries.toTypedArray().random()
```

The `title`, `body` and `channel_id` in the data payload are based on the selected `randomChannel`. The Android client 
will then receive the payload with the data and handle it in `onMessageReceived`. For the `channel_id`, if the Android 
client has the channel created it will use it, otherwise the channel will be created. [This is the functionality in the 
<a href="https://github.com/akitikkx/channel-me-perfect-android">sample Android app</a>'s `onMessageReceived`]
