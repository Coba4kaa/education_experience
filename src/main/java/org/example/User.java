package org.example;

import org.example.Notifications.NotificationChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a user.
 */
public class User {
    private String name;
    private String surname;
    private String address;
    private String passportData;
    private List<NotificationChannel> notificationChannels;

    /**
     * Constructs a new User with the specified name and surname.
     * @param name the name of the user.
     * @param surname the surname of the user.
     */
    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        notificationChannels = new ArrayList<>();
    }

    /**
     * Notifies the user with a message through all their notification channels.
     * @param message the message to be sent.
     */
    public void notify(String message) {
        if (!notificationChannels.isEmpty())
            for (NotificationChannel channel : notificationChannels)
                channel.send(message);
    }

    /**
     * Checks if the user is suspicious.
     * A user is considered suspicious if they don't have an address or passport data.
     * @return true if the user is suspicious, false otherwise.
     */
    public boolean isSuspicious() {
        return address == null || passportData == null;
    }

    /**
     * Sets the address of the user.
     * @param address the address to be set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the passport data of the user.
     * @param passportData the passport data to be set.
     */
    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    /**
     * Gets the surname of the user.
     * @return the surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Gets the name of the user.
     * @return the name.
     */
    public String getName() {
        return name;
    }
}
