package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Comment;
import com.example.demo.model.News;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.CommentRepo;
import com.example.demo.repo.NewsRepo;
import com.example.demo.service.NewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepo newsRepository;
    private final CategoryRepo categoryRepository;
    private final CommentRepo commentRepository;

    public NewsServiceImpl(NewsRepo newsRepository, CategoryRepo categoryRepository, CommentRepo commentRepository) {
        this.newsRepository = newsRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<News> getAllNews() {
        return newsRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public News getNewsById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<News> getNewsByCategory(Long categoryId) {
        return newsRepository.findByCategories_Id(categoryId);
    }
    @Override
    @Transactional(readOnly = true)
    public List<News> getNewsByCategoryName(String categoryName) {
        return newsRepository.findByCategories_Name(categoryName);
    }
    @Override
    @Transactional
    public News createNews(News news, Set<Long> categoryIds) {
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : categoryIds) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
            categories.add(category);
        }
        news.setCategories(categories);
        return newsRepository.save(news);
    }
    @Override
    @Transactional
    public News updateNews(Long id, News newsDetails, Set<Long> categoryIds) {
        News news = getNewsById(id);
        news.setHeadline(newsDetails.getHeadline());
        news.setContent(newsDetails.getContent());

        if (categoryIds != null && !categoryIds.isEmpty()) {
            Set<Category> categories = new HashSet<>();
            for (Long categoryId : categoryIds) {
                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
                categories.add(category);
            }
            news.setCategories(categories);
        }

        return newsRepository.save(news);
    }
    @Override
    @Transactional
    public void deleteNews(Long id) {
        News news = getNewsById(id);
        newsRepository.delete(news);
    }
    @Override
    @Transactional
    public Comment addComment(Long newsId, Comment comment) {
        News news = getNewsById(newsId);
        comment.setNews(news);
        return commentRepository.save(comment);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Comment> getCommentsByNewsId(Long newsId) {
        return commentRepository.findByNewsIdOrderByCreatedAtDesc(newsId);
    }

}
