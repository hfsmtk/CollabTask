package com.example.collab.repositories;

import com.example.collab.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** Repository JPA pour les tableaux (boards). */
public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByWorkspaceId(Long workspaceId);
}
