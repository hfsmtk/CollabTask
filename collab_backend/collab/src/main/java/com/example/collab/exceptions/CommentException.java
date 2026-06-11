package com.example.collab.exceptions;

/** Exception levée lorsqu'un commentaire est introuvable ou invalide. */
public class CommentException extends Exception {
    public CommentException(String message) {
        super(message);
    }
}
