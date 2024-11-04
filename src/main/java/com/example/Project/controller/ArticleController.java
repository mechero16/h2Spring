package com.example.Project.controller;

import com.example.Project.Repository.ArticleRepository;
import com.example.Project.model.Article;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return articleRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article existingArticle = articleRepository.findById(id).orElseThrow();
        existingArticle.setTitle(article.getTitle());
        existingArticle.setAuthor(article.getAuthor());
        existingArticle.setContent(article.getContent());
        return articleRepository.save(existingArticle);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
    }
}
