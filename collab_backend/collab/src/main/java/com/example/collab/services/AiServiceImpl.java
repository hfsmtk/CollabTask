package com.example.collab.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class AiServiceImpl implements AiService {

    private static final URI GROQ_CHAT_COMPLETIONS_URI =
            URI.create("https://api.groq.com/openai/v1/chat/completions");

    @Value("${groq.api.key:}")
    private String groqApiKey;

    @Value("${groq.model:llama-3.1-8b-instant}")
    private String model;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String generateTaskDescription(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title is required to generate a description.");
        }

        if (groqApiKey == null || groqApiKey.isBlank()) {
            throw new IllegalStateException("GROQ_API_KEY is not configured.");
        }

        try {
            Map<String, Object> requestBody = Map.of(
                    "model", model,
                    "messages", List.of(
                            Map.of(
                                    "role", "system",
                                    "content", "Generate one concise, practical task description for a project management app. Include key implementation expectations when useful. Return only the description text."
                            ),
                            Map.of(
                                    "role", "user",
                                    "content", "Task title: " + title.trim()
                            )
                    ),
                    "max_tokens", 160,
                    "temperature", 0.4
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(GROQ_CHAT_COMPLETIONS_URI)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + groqApiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException("Groq API returned status "
                        + response.statusCode() + ": " + response.body());
            }

            String generatedText = extractText(response.body());
            if (generatedText.isBlank()) {
                throw new IllegalStateException("Groq API returned an empty description.");
            }

            return generatedText;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate task description with Groq.", e);
        }
    }

    private String extractText(String responseBody) throws Exception {
        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode content = root.path("choices").path(0).path("message").path("content");

        if (content.isTextual()) {
            return content.asText().trim();
        }

        return "";
    }
}
