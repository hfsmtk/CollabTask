package com.example.collab.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entité représentant une notification envoyée à un utilisateur.
 * Les notifications référencent optionnellement une tâche et/ou un board source.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime createdAt;

    private boolean isRead = false;

    /**
     * Type d'événement déclencheur.
     * Valeurs connues : {@code TASK_CREATED}, {@code TASK_ASSIGNED}, {@code COMMENT_ADDED}.
     */
    private String type;

    /** Identifiant de la tâche concernée (peut être null). */
    private Long taskId;

    /** Identifiant du board concerné (peut être null). */
    private Long boardId;

    @ManyToOne
    private User ownerNotification;
}
