package com.example.collab.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entité représentant un utilisateur de l'application.
 * Un utilisateur peut posséder plusieurs workspaces, être assigné à des tâches
 * et rédiger des commentaires.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /** Email unique servant également d'identifiant de connexion. */
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    /** Mot de passe hashé via BCrypt — jamais stocké en clair. */
    private String password;

    private String avatarUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Workspace> workspaces;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    @OneToMany(mappedBy = "ownerNotification")
    private List<Notification> notifications;
}
