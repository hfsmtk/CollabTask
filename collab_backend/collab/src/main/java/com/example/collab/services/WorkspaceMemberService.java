package com.example.collab.services;

import com.example.collab.dtos.WorkspaceDTO;
import com.example.collab.dtos.WorkspaceMemberDTO;
import com.example.collab.enums.WorkspaceRole;
import com.example.collab.exceptions.UserNotFoundException;
import com.example.collab.exceptions.WorkspaceException;

import java.util.List;

/**
 * Contrat de service pour la gestion des workspaces et de leurs membres.
 * Les opérations sensibles (suppression, gestion des membres, changement de rôle)
 * vérifient le rôle de l'utilisateur connecté via le SecurityContextHolder.
 */
public interface WorkspaceMemberService {

    /** Retourne tous les workspaces existants (usage admin). */
    List<WorkspaceDTO> getAllWorkspaces();

    /**
     * Récupère un workspace par son slug URL-friendly.
     *
     * @throws WorkspaceException si le slug ne correspond à aucun workspace
     */
    WorkspaceDTO getWorkspaceBySlug(String slug) throws WorkspaceException;

    /**
     * Récupère un workspace par son identifiant.
     *
     * @throws WorkspaceException si le workspace n'existe pas
     */
    WorkspaceDTO getWorkspaceById(Long id) throws WorkspaceException;

    /**
     * Met à jour le nom et la description d'un workspace (recalcule le slug).
     *
     * @throws WorkspaceException    si le workspace n'existe pas
     * @throws UserNotFoundException si l'owner référencé est introuvable
     */
    WorkspaceDTO updateWorkspace(WorkspaceDTO workspaceDTO, Long id) throws WorkspaceException, UserNotFoundException;

    /**
     * Crée un nouveau workspace et ajoute automatiquement le créateur comme membre OWNER.
     *
     * @throws WorkspaceException    si un workspace du même nom existe déjà
     * @throws UserNotFoundException si l'owner est introuvable
     */
    WorkspaceDTO saveWorkspace(WorkspaceDTO workspaceDTO) throws WorkspaceException, UserNotFoundException;

    /**
     * Supprime un workspace. Requiert le rôle OWNER.
     *
     * @throws WorkspaceException    si le workspace n'existe pas ou droits insuffisants
     * @throws UserNotFoundException si l'utilisateur connecté est introuvable
     */
    WorkspaceDTO deleteWorkspace(Long id) throws WorkspaceException, UserNotFoundException;

    /**
     * Ajoute un utilisateur comme membre MEMBER du workspace.
     * Requiert le rôle OWNER ou ADMIN. L'owner ne peut pas être re-ajouté.
     *
     * @throws UserNotFoundException si l'utilisateur est introuvable
     * @throws WorkspaceException    si le workspace est introuvable, droits insuffisants,
     *                               ou l'utilisateur est déjà membre
     */
    void addMember(Long workspaceId, Long userId) throws UserNotFoundException, WorkspaceException;

    /**
     * Retire un membre du workspace. L'owner ne peut pas être retiré.
     * Requiert le rôle OWNER ou ADMIN.
     *
     * @throws UserNotFoundException si l'utilisateur est introuvable
     * @throws WorkspaceException    si le membre n'existe pas ou droits insuffisants
     */
    void removeMember(Long workspaceId, Long userId) throws UserNotFoundException, WorkspaceException;

    /**
     * Retourne la liste des membres d'un workspace avec leurs rôles.
     *
     * @throws WorkspaceException si le workspace est introuvable
     */
    List<WorkspaceMemberDTO> getWorkspaceMembers(Long workspaceId) throws WorkspaceException;

    /**
     * Retourne tous les workspaces auxquels un utilisateur appartient,
     * en incluant son rôle dans chaque workspace.
     *
     * @throws WorkspaceException si une erreur survient
     */
    List<WorkspaceDTO> getWorkspacesByUser(Long userId) throws WorkspaceException;

    /**
     * Change le rôle d'un membre. Seul l'OWNER peut effectuer cette action.
     * Le rôle de l'owner lui-même ne peut pas être modifié.
     *
     * @throws UserNotFoundException si l'utilisateur est introuvable
     * @throws WorkspaceException    si le membre n'existe pas, droits insuffisants,
     *                               ou tentative de modifier le rôle de l'owner
     */
    void changeRole(Long workspaceId, Long userId, WorkspaceRole newRole) throws UserNotFoundException, WorkspaceException;
}
