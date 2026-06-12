package com.example.collab.exceptions;

/** Exception levée lors d'une tentative de création d'un utilisateur avec un email déjà utilisé. */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
