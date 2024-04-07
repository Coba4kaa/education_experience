package org.example.notifications;

/**
 * This interface represents a notification channel.
 */
public interface NotificationChannel {
    /**
     * Sends a message through the notification channel.
     *
     * @param message the message to be sent.
     */
    void send(String message);
}
