package com.example.collab.exceptions;

/** Exception levée lorsqu'un workspace est introuvable, invalide, ou que l'accès est interdit. */
public class WorkspaceException extends RuntimeException {
    public WorkspaceException(String message) {
        super(message);
    }
}
