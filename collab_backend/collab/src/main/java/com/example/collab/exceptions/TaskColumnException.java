package com.example.collab.exceptions;

/** Exception levée lorsqu'une colonne Kanban est introuvable ou invalide. */
public class TaskColumnException extends Exception {
    public TaskColumnException(String message) {
        super(message);
    }
}
