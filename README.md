# Ktor: Firebase Admin Sample Backend

## About the application

This is a sample Ktor project that aims to demonstrate using Firebase Admin SDK to send push notifications to specific 
Android devices that have the `com.google.firebase:firebase-messaging` SDK installed and actively listening for new 
messages. [See <a href="https://github.com/akitikkx/channel-me-perfect-android">here</a> a sample Android app I 
created to interact/react with the server].

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

Store this file in the `src/main/resources` folder. The `src/main/kotlin/com/example/Application.kt` expects the filename to 
be `ktor-firebase-auth.json`. Feel free to name the file how you would want and remember to update the name inside the 
`getResourceAsStream()` call in `src/main/kotlin/com/example/Application.kt`

## Dependencies
- Firebase Admin `com.google.firebase:firebase-admin`
- Content Negotiation `io.ktor:ktor-server-content-negotiation`
- Serialization `io.ktor:ktor-serialization-gson`