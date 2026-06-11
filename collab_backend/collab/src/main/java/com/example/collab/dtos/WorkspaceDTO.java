package com.example.collab.dtos;


import com.example.collab.entities.Board;
import com.example.collab.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/** DTO de transfert pour un workspace. {@code myRole} est renseigné lors des requêtes par utilisateur. */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceDTO {

    private Long id ;
    private String name ;
    private String description ;
    private String slug ;

    private Long ownerId ;
    private String myRole ;
   // private String ownerName;

}
