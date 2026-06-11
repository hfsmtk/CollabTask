package com.example.collab.services;

import com.example.collab.dtos.BoardDTO;
import com.example.collab.exceptions.BoardException;
import com.example.collab.exceptions.WorkspaceException;

import java.util.List;

/**
 * Contrat de service pour la gestion des boards Kanban.
 * Les opérations de modification vérifient les droits de l'utilisateur connecté
 * (OWNER ou ADMIN du workspace).
 */
public interface BoardService {

    /**
     * Récupère un board par son identifiant.
     *
     * @param id identifiant du board
     * @return le DTO du board
     * @throws BoardException si le board n'existe pas
     */
    BoardDTO getBoard(Long id) throws BoardException;

    /**
     * Crée un nouveau board dans un workspace.
     * Requiert le rôle OWNER ou ADMIN sur ce workspace.
     *
     * @param boardDTO données du board à créer
     * @return le DTO du board créé
     * @throws BoardException si le workspace est introuvable ou droits insuffisants
     */
    BoardDTO createBoard(BoardDTO boardDTO) throws BoardException;

    /**
     * Met à jour le titre, la couleur et le statut favori d'un board.
     * Requiert le rôle OWNER ou ADMIN sur le workspace du board.
     *
     * @param id      identifiant du board à modifier
     * @param boardDTO nouvelles valeurs
     * @return le DTO mis à jour
     * @throws BoardException si le board est introuvable ou droits insuffisants
     */
    BoardDTO updateBoard(Long id, BoardDTO boardDTO) throws BoardException;

    /**
     * Supprime un board et toutes ses colonnes/tâches en cascade.
     * Requiert le rôle OWNER ou ADMIN.
     *
     * @param id identifiant du board
     * @throws BoardException si le board est introuvable ou droits insuffisants
     */
    void deleteBoard(Long id) throws BoardException;

    /**
     * Retourne tous les boards d'un workspace.
     *
     * @param workspaceId identifiant du workspace
     * @return liste des boards
     * @throws BoardException     si une erreur survient
     * @throws WorkspaceException si le workspace est introuvable
     */
    List<BoardDTO> getBoards(Long workspaceId) throws BoardException, WorkspaceException;

    /**
     * Inverse le statut favori du board.
     *
     * @param id identifiant du board
     * @return le DTO mis à jour
     * @throws BoardException si le board est introuvable
     */
    BoardDTO toggleFavorite(Long id) throws BoardException;

    /**
     * Met à jour uniquement la couleur de fond du board.
     * Requiert le rôle OWNER ou ADMIN.
     *
     * @param id    identifiant du board
     * @param color code couleur hexadécimal
     * @throws BoardException si le board est introuvable ou droits insuffisants
     */
    void updateBoardColor(Long id, String color) throws BoardException;
}
