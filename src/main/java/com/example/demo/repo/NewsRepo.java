package com.example.demo.repo;

import com.example.demo.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface NewsRepo extends JpaRepository<News,Long> {

    // Get all news ordered by createdAt descending
    List<News> findAllByOrderByCreatedAtDesc();

    // Find news by category ID
    List<News> findByCategories_Id(Long categoryId);

    // Find news by category name
    List<News> findByCategories_Name(String categoryName);

}
