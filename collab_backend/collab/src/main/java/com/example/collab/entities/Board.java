package com.example.collab.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entité représentant un tableau Kanban (board).
 * Un board appartient à un workspace et contient des colonnes de tâches ordonnées.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "boards")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    /** Couleur de fond affichée dans l'interface (ex. "#4F46E5"). */
    private String backgroundColor;

    private Boolean isFavorite = false;

    @ManyToOne
    @JoinColumn(name = "workspace_id", nullable = false)
    private Workspace workspace;

    /** Colonnes triées par position croissante. */
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    private List<TaskColumn> taskColumns;
}
