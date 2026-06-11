package com.example.collab.services;

/**
 * Contrat de service pour les fonctionnalités d'intelligence artificielle.
 * L'implémentation utilise l'API Groq (modèle LLaMA) pour générer du contenu.
 */
public interface AiService {

    /**
     * Génère une description concise et pratique pour une tâche à partir de son titre.
     * Appelle l'API Groq en synchrone.
     *
     * @param title titre de la tâche (ne doit pas être vide)
     * @return description générée par le modèle
     * @throws IllegalArgumentException si le titre est null ou vide
     * @throws IllegalStateException    si la clé API Groq n'est pas configurée
     * @throws RuntimeException         si l'appel à l'API échoue
     */
    String generateTaskDescription(String title);
}
