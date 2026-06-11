package com.example.collab.repositories;

import com.example.collab.entities.TaskColumn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** Repository JPA pour les colonnes Kanban. */
public interface TaskColumnRepository extends JpaRepository<TaskColumn,Long> {
    List<TaskColumn> findByBoardIdOrderByPositionAsc(Long boardId);
}
