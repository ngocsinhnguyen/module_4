package com.example.module4_s6_blog.service;

import com.example.module4_s6_blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBlogService {
    Page<Blog> findAll(Pageable pageable);
    Optional<Blog> findById(Long id);
    Blog save(Blog blog);
    void deleteById(Long id);
    Page<Blog> findByCategoryId(Long categoryId, Pageable pageable);
}
