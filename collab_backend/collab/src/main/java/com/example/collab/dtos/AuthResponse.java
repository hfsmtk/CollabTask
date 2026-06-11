package com.example.collab.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/** DTO retourné après un login ou un register réussi. Contient le token JWT et les infos de session. */
@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private Long userId;
    private String name;
    private String email;
    private Long workspaceId;
    private String role;

}
