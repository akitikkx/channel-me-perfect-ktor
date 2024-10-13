package com.example.models.android

/**
 * Represents the proxy behavior for a notification.
 */
enum class Proxy {
    /**
     * If unspecified, default to Proxy.IF_PRIORITY_LOWERED.
     */
    PROXY_UNSPECIFIED,

    /**
     * Try to proxy this notification.
     */
    ALLOW,

    /**
     * Do not proxy this notification.
     */
    DENY,

    /**
     * Only try to proxy this notification if its AndroidMessagePriority was lowered from HIGH to NORMAL on the device.
     */
    IF_PRIORITY_LOWERED
}