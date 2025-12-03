package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.News;

import java.util.List;
import java.util.Set;

public interface NewsService {

    List<News> getAllNews();

    News getNewsById(Long id);

    List<News> getNewsByCategory(Long categoryId);

    List<News> getNewsByCategoryName(String categoryName);

    News createNews(News news, Set<Long> categoryIds);

    News updateNews(Long id, News newsDetails, Set<Long> categoryIds);

    void deleteNews(Long id);

    Comment addComment(Long newsId, Comment comment);

    List<Comment> getCommentsByNewsId(Long newsId);
}
