package com.example.collab.web;

import com.example.collab.dtos.AiRequest;
import com.example.collab.dtos.AiResponse;
import com.example.collab.services.AiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Contrôleur REST exposant les fonctionnalités d'IA (génération de description de tâche via Groq).
 * Les erreurs {@link IllegalArgumentException}, {@link IllegalStateException} et {@link RuntimeException}
 * sont interceptées localement et retournées en 502 Bad Gateway.
 */
@RestController
@RequestMapping("/api/ai")
@AllArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/generate-description")
    public AiResponse generateDescription(@RequestBody AiRequest request) {
        String description = aiService.generateTaskDescription(request.getTitle());
        return new AiResponse(description);
    }

    @ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class, RuntimeException.class })
    public ResponseEntity<Map<String, String>> handleAiError(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(Map.of("message", rootCauseMessage(exception)));
    }

    private String rootCauseMessage(Throwable throwable) {
        Throwable current = throwable;
        while (current.getCause() != null) {
            current = current.getCause();
        }
        return current.getMessage() != null ? current.getMessage() : "AI generation failed.";
    }
}
