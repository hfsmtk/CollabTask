package com.example.collab.exceptions;

/** Exception levée lorsqu'un tableau (board) est introuvable ou invalide. */
public class BoardException extends Exception {
    public BoardException(String message) {
        super(message);
    }
}
