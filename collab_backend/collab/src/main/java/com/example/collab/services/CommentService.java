package com.example.collab.services;

import com.example.collab.dtos.CommentDTO;
import com.example.collab.exceptions.CommentException;
import com.example.collab.exceptions.TaskException;
import com.example.collab.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Contrat de service pour la gestion des commentaires sur les tâches.
 */
public interface CommentService {

    /**
     * Crée un commentaire sur une tâche.
     *
     * @param commentDTO données du commentaire (taskId et authorId obligatoires)
     * @return le DTO du commentaire créé
     * @throws TaskException         si la tâche est introuvable
     * @throws UserNotFoundException si l'auteur est introuvable
     */
    CommentDTO saveComment(CommentDTO commentDTO) throws TaskException, UserNotFoundException;

    /**
     * Met à jour le contenu d'un commentaire existant.
     *
     * @param id         identifiant du commentaire
     * @param commentDTO nouvelles valeurs
     * @return le DTO mis à jour
     * @throws CommentException      si le commentaire est introuvable
     * @throws UserNotFoundException si l'auteur est introuvable
     * @throws TaskException         si la tâche est introuvable
     */
    CommentDTO updateComment(Long id, CommentDTO commentDTO) throws CommentException, UserNotFoundException, TaskException;

    /**
     * Supprime un commentaire et retourne son DTO avant suppression.
     *
     * @param id identifiant du commentaire
     * @return le DTO du commentaire supprimé
     * @throws CommentException      si le commentaire est introuvable
     * @throws UserNotFoundException si l'auteur est introuvable
     * @throws TaskException         si la tâche est introuvable
     */
    CommentDTO deleteComment(Long id) throws CommentException, UserNotFoundException, TaskException;

    /**
     * Récupère un commentaire par son identifiant.
     *
     * @param id identifiant du commentaire
     * @return le DTO du commentaire
     * @throws CommentException      si le commentaire est introuvable
     * @throws UserNotFoundException si l'auteur est introuvable
     * @throws TaskException         si la tâche est introuvable
     */
    CommentDTO getComment(Long id) throws UserNotFoundException, TaskException, CommentException;

    /**
     * Retourne tous les commentaires rédigés par un utilisateur.
     *
     * @param userId identifiant de l'utilisateur
     * @return liste des commentaires
     * @throws UserNotFoundException si l'utilisateur est introuvable
     */
    List<CommentDTO> getCommentByUser(Long userId) throws UserNotFoundException;

    /**
     * Retourne tous les commentaires d'une tâche.
     *
     * @param taskId identifiant de la tâche
     * @return liste des commentaires
     * @throws UserNotFoundException si une erreur survient
     */
    List<CommentDTO> getCommentsByTask(Long taskId) throws UserNotFoundException;
}
