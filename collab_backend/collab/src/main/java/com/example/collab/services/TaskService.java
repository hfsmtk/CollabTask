package com.example.collab.services;

import com.example.collab.dtos.TaskDTO;
import com.example.collab.exceptions.TaskColumnException;
import com.example.collab.exceptions.TaskException;
import com.example.collab.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Contrat de service pour la gestion des tâches.
 */
public interface TaskService {

    /**
     * Crée une nouvelle tâche dans une colonne. Si un assignee est fourni,
     * une notification lui est envoyée automatiquement.
     *
     * @param taskDTO données de la tâche
     * @return le DTO de la tâche créée
     * @throws TaskException         si la colonne est introuvable
     * @throws UserNotFoundException si l'assignee est introuvable
     */
    TaskDTO saveTask(TaskDTO taskDTO) throws TaskException, UserNotFoundException;

    /**
     * Récupère une tâche par son identifiant.
     *
     * @param id identifiant de la tâche
     * @return le DTO de la tâche
     * @throws TaskException si la tâche n'existe pas
     */
    TaskDTO getTaskById(Long id) throws TaskException;

    /**
     * Récupère une tâche par son titre exact.
     *
     * @param title titre de la tâche
     * @return le DTO de la tâche
     * @throws TaskException si aucune tâche ne correspond
     */
    TaskDTO getTaskByTitle(String title) throws TaskException;

    /**
     * Met à jour les propriétés d'une tâche (titre, description, priorité, assignee, échéance).
     *
     * @param id      identifiant de la tâche
     * @param taskDTO nouvelles valeurs
     * @return le DTO mis à jour
     * @throws TaskException         si la tâche est introuvable
     * @throws UserNotFoundException si le nouvel assignee est introuvable
     */
    TaskDTO updateTask(Long id, TaskDTO taskDTO) throws TaskException, UserNotFoundException;

    /**
     * Supprime une tâche et retourne son DTO avant suppression.
     *
     * @param id identifiant de la tâche
     * @return le DTO de la tâche supprimée
     * @throws TaskException si la tâche est introuvable
     */
    TaskDTO deleteTask(Long id) throws TaskException;

    /**
     * Retourne toutes les tâches d'une colonne donnée.
     *
     * @param taskColumnId identifiant de la colonne
     * @return liste des tâches
     * @throws TaskColumnException si la colonne est introuvable
     */
    List<TaskDTO> getAllTasksByTaskColumn(Long taskColumnId) throws TaskColumnException;

    /**
     * Retourne toutes les tâches assignées à un utilisateur.
     *
     * @param assigneeId identifiant de l'utilisateur
     * @return liste des tâches assignées
     * @throws UserNotFoundException si l'utilisateur est introuvable
     */
    List<TaskDTO> getAllTasksByAssignee(Long assigneeId) throws UserNotFoundException;

    /**
     * Déplace une tâche vers une autre colonne et la positionne en fin de liste.
     *
     * @param taskId        identifiant de la tâche
     * @param targetColumnId identifiant de la colonne cible
     * @return le DTO mis à jour
     * @throws TaskException       si la tâche est introuvable
     * @throws TaskColumnException si la colonne cible est introuvable
     */
    TaskDTO moveTask(Long taskId, Long targetColumnId) throws TaskException, TaskColumnException;
}
