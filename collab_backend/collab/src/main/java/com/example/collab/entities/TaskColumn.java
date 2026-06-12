package com.example.collab.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entité représentant une colonne dans un board Kanban.
 * Chaque colonne a un nom, une position et contient une liste de tâches.
 */
@Table(name = "tasks_columns")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskColumn {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /** Ordre d'affichage de la colonne dans le board (0-based). */
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "taskColumn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
