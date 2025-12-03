package com.example.demo.controller;// NewsController.jav
import com.example.demo.dto.CommentRequest;
import com.example.demo.dto.NewsRequest;
import com.example.demo.model.Comment;
import com.example.demo.model.News;
import com.example.demo.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<News>> getNewsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(newsService.getNewsByCategory(categoryId));
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody NewsRequest newsRequest) {
        News news = new News();
        news.setHeadline(newsRequest.getHeadline());
        news.setContent(newsRequest.getContent());

        News createdNews = newsService.createNews(news, newsRequest.getCategoryIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id,
                                           @RequestBody NewsRequest newsRequest) {
        News news = new News();
        news.setHeadline(newsRequest.getHeadline());
        news.setContent(newsRequest.getContent());

        News updatedNews = newsService.updateNews(id, news, newsRequest.getCategoryIds());
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{newsId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long newsId,
                                              @RequestBody CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setAuthor(commentRequest.getAuthor());
        comment.setContent(commentRequest.getContent());

        Comment createdComment = newsService.addComment(newsId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/{newsId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByNewsId(@PathVariable Long newsId) {
        return ResponseEntity.ok(newsService.getCommentsByNewsId(newsId));
    }
}