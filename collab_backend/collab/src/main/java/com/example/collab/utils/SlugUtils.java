package com.example.collab.utils;

/**
 * Utilitaire de génération de slugs URL-friendly.
 * Classe utilitaire non instanciable (constructeur privé).
 */
public final class SlugUtils {

    private SlugUtils() {}

    /**
     * Convertit une chaîne en slug : minuscules, caractères non-alphanumériques remplacés
     * par des tirets, tirets consécutifs fusionnés, tirets de début/fin supprimés.
     *
     * @param input texte source (ex. "Mon Workspace!")
     * @return slug (ex. "mon-workspace")
     */
    public static String toSlug(String input) {
        return input.toLowerCase()
                .replaceAll("[^a-z0-9]", "-")
                .replaceAll("-+", "-")
                .replaceAll("^-|-$", "");
    }
}
