package com.example.collab.entities;

import com.example.collab.enums.EnumPriority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant une tâche dans une colonne Kanban.
 * Une tâche peut être assignée à un membre, étiquetée et avoir des commentaires.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private EnumPriority priority;

    /** Position de la tâche dans sa colonne (0-based). */
    private Integer position;

    @CreationTimestamp
    private LocalDateTime createdAt;

    /** Date limite d'exécution. Stockée en LocalDateTime, exposée en LocalDate via le DTO. */
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "taskColumn_id", nullable = false)
    private TaskColumn taskColumn;

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "task_labels",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<Label> labels = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;
}
