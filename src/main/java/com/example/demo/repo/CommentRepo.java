package com.example.demo.repo;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CommentRepo extends JpaRepository<Comment,Long> {
    // Get comments by news ID, ordered by createdAt descending
    List<Comment> findByNewsIdOrderByCreatedAtDesc(Long newsId);
}
