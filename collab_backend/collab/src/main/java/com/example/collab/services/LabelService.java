package com.example.collab.services;

import com.example.collab.dtos.LabelDTO;
import com.example.collab.dtos.TaskDTO;
import com.example.collab.exceptions.LabelException;
import com.example.collab.exceptions.TaskException;
import com.example.collab.exceptions.WorkspaceException;

import java.util.List;

/**
 * Contrat de service pour la gestion des labels et leur association aux tâches.
 * Les labels sont définis au niveau du workspace.
 */
public interface LabelService {

    /**
     * Crée un nouveau label dans un workspace.
     *
     * @param labelDTO données du label (name, color, workspaceId)
     * @return le DTO du label créé
     * @throws WorkspaceException si le workspace est introuvable
     */
    LabelDTO createLabel(LabelDTO labelDTO) throws WorkspaceException;

    /**
     * Retourne tous les labels définis dans un workspace.
     *
     * @param workspaceId identifiant du workspace
     * @return liste des labels
     */
    List<LabelDTO> getLabelsByWorkspace(Long workspaceId);

    /**
     * Attache un label existant à une tâche.
     *
     * @param taskId  identifiant de la tâche
     * @param labelId identifiant du label
     * @return le DTO de la tâche mis à jour
     * @throws LabelException si le label est introuvable
     * @throws TaskException  si la tâche est introuvable
     */
    TaskDTO addLabelToTask(Long taskId, Long labelId) throws LabelException, TaskException;

    /**
     * Retire un label d'une tâche.
     *
     * @param taskId  identifiant de la tâche
     * @param labelId identifiant du label
     * @return le DTO de la tâche mis à jour
     * @throws LabelException si le label est introuvable
     * @throws TaskException  si la tâche est introuvable
     */
    TaskDTO removeLabelToTask(Long taskId, Long labelId) throws LabelException, TaskException;
}
