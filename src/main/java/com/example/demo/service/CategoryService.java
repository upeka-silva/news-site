package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;

public interface CategoryService {
    // Retrieve all categories
    List<Category> getAllCategories();

    // Retrieve a category by its ID
    Category getCategoryById(Long id);

    // Create a new category
    Category createCategory(Category category);

    // Update an existing category
    Category updateCategory(Long id, Category categoryDetails);

    // Delete a category by its ID
    void deleteCategory(Long id);

}
