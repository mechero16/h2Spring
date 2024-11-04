package com.example.Project.controller;

import com.example.Project.Repository.PageRepository;
import com.example.Project.model.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/pages")
public class PageController {

    private final PageRepository pageRepository;

    public PageController(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @GetMapping("/{title}")
    public Page getPageByTitle(@PathVariable String title) {
        return pageRepository.findByTitle(title);
    }

    @GetMapping
    public List<Page> getAllPages() {
        return pageRepository.findAll();
    }

    @PostMapping
    public Page createPage(@RequestBody Page page) {
        return pageRepository.save(page);
    }

    @PutMapping("/{id}")
    public Page updatePage(@PathVariable Long id, @RequestBody Page page) {
        Page existingPage = pageRepository.findById(id).orElseThrow();
        existingPage.setTitle(page.getTitle());
        existingPage.setContent(page.getContent());
        return pageRepository.save(existingPage);
    }

    @DeleteMapping("/{id}")
    public void deletePage(@PathVariable Long id) {
        pageRepository.deleteById(id);
    }
}
