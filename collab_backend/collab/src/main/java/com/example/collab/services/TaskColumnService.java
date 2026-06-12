package com.example.collab.services;

import com.example.collab.dtos.TaskColumnDTO;
import com.example.collab.exceptions.TaskColumnException;

import java.util.List;

/**
 * Contrat de service pour la gestion des colonnes d'un board Kanban.
 */
public interface TaskColumnService {

    /**
     * Crée une nouvelle colonne et l'associe au board spécifié.
     *
     * @param taskColumnDTO données de la colonne (name, position, boardId)
     * @return le DTO de la colonne créée
     */
    TaskColumnDTO createColumn(TaskColumnDTO taskColumnDTO);

    /**
     * Met à jour le nom et la position d'une colonne.
     *
     * @param id            identifiant de la colonne
     * @param taskColumnDTO nouvelles valeurs
     * @return le DTO mis à jour
     * @throws TaskColumnException si la colonne est introuvable
     */
    TaskColumnDTO updateColumn(Long id, TaskColumnDTO taskColumnDTO) throws TaskColumnException;

    /**
     * Supprime une colonne et toutes ses tâches (cascade).
     *
     * @param id identifiant de la colonne
     * @return le DTO de la colonne supprimée
     * @throws TaskColumnException si la colonne est introuvable
     */
    TaskColumnDTO deleteColumn(Long id) throws TaskColumnException;

    /**
     * Récupère une colonne par son identifiant.
     *
     * @param id identifiant de la colonne
     * @return le DTO de la colonne
     * @throws TaskColumnException si la colonne est introuvable
     */
    TaskColumnDTO getColumn(Long id) throws TaskColumnException;

    /**
     * Retourne toutes les colonnes d'un board, triées par position.
     *
     * @param boardId identifiant du board
     * @return liste des colonnes
     * @throws TaskColumnException si le board est introuvable
     */
    List<TaskColumnDTO> getColumnsByBoard(Long boardId) throws TaskColumnException;
}
