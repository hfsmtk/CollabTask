package com.example.collab.exceptions;

/** Exception levée lorsqu'une tâche est introuvable ou invalide. */
public class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}
