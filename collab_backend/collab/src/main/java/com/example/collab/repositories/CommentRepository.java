package com.example.collab.repositories;

import com.example.collab.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository JPA pour les commentaires. */
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
