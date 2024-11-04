package com.example.Project.controller;

import com.example.Project.Repository.PageRepository;
import com.example.Project.model.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PageRepository pageRepository;

    public AdminController(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @GetMapping("/pages")
    public String getPages(Model model) {
        model.addAttribute("pages", pageRepository.findAll());
        return "admin/pages";
    }

    @GetMapping("/pages/create")
    public String createPageForm(Model model) {
        model.addAttribute("page", new Page());
        return "admin/create-page";
    }

    @PostMapping("/pages")
    public String createPage(@ModelAttribute Page page) {
        pageRepository.save(page);
        return "redirect:/admin/pages";
    }

    @GetMapping("/pages/edit/{id}")
    public String editPageForm(@PathVariable Long id, Model model) {
        model.addAttribute("page", pageRepository.findById(id).orElseThrow());
        return "admin/edit-page";
    }

    @PostMapping("/pages/edit/{id}")
    public String updatePage(@PathVariable Long id, @ModelAttribute Page page) {
        Page existingPage = pageRepository.findById(id).orElseThrow();
        existingPage.setTitle(page.getTitle());
        existingPage.setContent(page.getContent());
        pageRepository.save(existingPage);
        return "redirect:/admin/pages";
    }

    @GetMapping("/pages/delete/{id}")
    public String deletePage(@PathVariable Long id) {
        pageRepository.deleteById(id);
        return "redirect:/admin/pages";
    }
}
