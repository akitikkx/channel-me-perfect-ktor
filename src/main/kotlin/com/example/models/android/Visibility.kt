package com.example.models.android

/**
 * Represents the visibility of a notification on the device's lockscreen.
 */
enum class Visibility {
    /**
     * If unspecified, default to Visibility.PRIVATE.
     */
    VISIBILITY_UNSPECIFIED,

    /**
     * Show this notification on all lockscreens, but conceal sensitive or private information on secure lockscreens.
     */
    VISIBILITY_PRIVATE,

    /**
     * Show this notification in its entirety on all lockscreens.
     */
    VISIBILITY_PUBLIC,

    /**
     * Do not reveal any part of this notification on a secure lockscreen.
     */
    VISIBILITY_SECRET
}