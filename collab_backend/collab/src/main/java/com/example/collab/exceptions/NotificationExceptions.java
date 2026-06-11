package com.example.collab.exceptions;

/** Exception levée lorsqu'une notification est introuvable. */
public class NotificationExceptions extends Exception {
    public NotificationExceptions(String message) {
        super(message);
    }
}
