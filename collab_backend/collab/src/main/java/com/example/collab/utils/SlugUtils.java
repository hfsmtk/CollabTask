package com.example.collab.utils;

public final class SlugUtils {

    private SlugUtils() {}

    public static String toSlug(String input) {
        return input.toLowerCase()
                .replaceAll("[^a-z0-9]", "-")
                .replaceAll("-+", "-")
                .replaceAll("^-|-$", "");
    }
}
