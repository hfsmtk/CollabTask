package com.example.collab.services;

import com.example.collab.dtos.NotificationDTO;
import com.example.collab.exceptions.NotificationExceptions;

import java.util.List;

/**
 * Contrat de service pour la gestion des notifications utilisateur.
 */
public interface NotificationService {

    /**
     * Retourne toutes les notifications non lues d'un utilisateur, triées par date décroissante.
     *
     * @param userId identifiant de l'utilisateur
     * @return liste des notifications non lues
     * @throws NotificationExceptions si une erreur survient
     */
    List<NotificationDTO> getUnreadNotification(Long userId) throws NotificationExceptions;

    /**
     * Marque une notification comme lue.
     *
     * @param notificationId identifiant de la notification
     * @throws NotificationExceptions si la notification est introuvable
     */
    void markedRead(Long notificationId) throws NotificationExceptions;

    /**
     * Crée et persiste une notification pour un utilisateur.
     * Les paramètres {@code taskId} et {@code boardId} sont optionnels.
     *
     * @param userId    destinataire de la notification
     * @param message   texte affiché
     * @param type      type d'événement (ex. TASK_CREATED, TASK_ASSIGNED, COMMENT_ADDED)
     * @param taskId    identifiant de la tâche concernée (peut être null)
     * @param boardId   identifiant du board concerné (peut être null)
     */
    void createNotification(Long userId, String message, String type, Long taskId, Long boardId);
}
