package com.example.collab.dtos;


import lombok.Data;

/** DTO utilisé pour les requêtes d'inscription et de connexion. */
@Data
public class RegisterRequest {

    private String name ;
    private String email ;
    private String password;
}
