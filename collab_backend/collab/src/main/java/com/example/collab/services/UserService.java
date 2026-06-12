package com.example.collab.services;

import com.example.collab.dtos.UserDTO;
import com.example.collab.exceptions.UserAlreadyExistsException;
import com.example.collab.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Contrat de service pour la gestion des utilisateurs.
 */
public interface UserService {

    /**
     * Crée un nouvel utilisateur après vérification des doublons (email/nom).
     *
     * @param userDTO données de l'utilisateur
     * @return le DTO de l'utilisateur créé
     * @throws UserAlreadyExistsException si l'email ou le nom est déjà utilisé
     */
    UserDTO saveUser(UserDTO userDTO) throws UserAlreadyExistsException;

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param id identifiant de l'utilisateur
     * @return le DTO de l'utilisateur
     * @throws UserNotFoundException si l'utilisateur n'existe pas
     */
    UserDTO getUser(Long id) throws UserNotFoundException;

    /**
     * Met à jour le nom, l'email et l'URL d'avatar d'un utilisateur.
     *
     * @param id          identifiant de l'utilisateur
     * @param userDetails nouvelles valeurs
     * @return le DTO mis à jour
     * @throws UserNotFoundException si l'utilisateur est introuvable
     */
    UserDTO updateUser(Long id, UserDTO userDetails) throws UserNotFoundException;

    /**
     * Supprime un utilisateur et retourne son DTO avant suppression.
     *
     * @param id identifiant de l'utilisateur
     * @return le DTO de l'utilisateur supprimé
     * @throws UserNotFoundException si l'utilisateur est introuvable
     */
    UserDTO deleteUser(Long id) throws UserNotFoundException;

    /**
     * Recherche des utilisateurs par email et/ou nom (correspondance partielle, insensible à la casse).
     * Au moins un critère doit être fourni.
     *
     * @param email fragment d'email (peut être null)
     * @param name  fragment de nom (peut être null)
     * @return liste des utilisateurs correspondants
     * @throws UserNotFoundException si aucun résultat ou aucun critère fourni
     */
    List<UserDTO> searchUser(String email, String name) throws UserNotFoundException;

    /** Retourne tous les utilisateurs enregistrés. */
    List<UserDTO> getAllUsers();
}
