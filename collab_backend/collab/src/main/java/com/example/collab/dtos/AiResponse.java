package com.example.collab.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/** DTO de la réponse contenant la description générée par l'IA. */
@Data
@AllArgsConstructor
public class AiResponse {
    private String description;
}