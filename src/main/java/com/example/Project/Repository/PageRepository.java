package com.example.Project.Repository;

import com.example.Project.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {
    Page findByTitle(String title);
}
