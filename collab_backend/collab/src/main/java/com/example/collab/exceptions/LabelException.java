package com.example.collab.exceptions;

/** Exception levée lorsqu'un label est introuvable ou invalide. */
public class LabelException extends Exception {
    public LabelException(String message) {
        super(message);
    }
}
