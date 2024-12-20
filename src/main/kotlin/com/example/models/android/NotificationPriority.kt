package com.example.models.android

/**
 * Represents the priority of a notification.
 */
enum class NotificationPriority {
    /**
     * If priority is unspecified, notification priority is set to PRIORITY_DEFAULT.
     */
    PRIORITY_UNSPECIFIED,

    /**
     * Lowest notification priority. Notifications with this PRIORITY_MIN might not be shown to the user except under special circumstances, such as detailed notification logs.
     */
    PRIORITY_MIN,

    /**
     * Lower notification priority. The UI may choose to show the notifications smaller, or at a different position in the list, compared with notifications with PRIORITY_DEFAULT.
     */
    PRIORITY_LOW,

    /**
     * Default notification priority. If the application does not prioritize its own notifications, use this value for all notifications.
     */
    PRIORITY_DEFAULT,

    /**
     * Higher notification priority. Use this for more important notifications or alerts. The UI may choose to show these notifications larger, or at a different position in the notification lists, compared with notifications with PRIORITY_DEFAULT.
     */
    PRIORITY_HIGH,

    /**
     * Highest notification priority. Use this for the application's most important items that require the user's prompt attention or input.
     */
    PRIORITY_MAX
}