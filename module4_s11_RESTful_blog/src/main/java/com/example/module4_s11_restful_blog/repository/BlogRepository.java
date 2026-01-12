package com.example.module4_s11_restful_blog.repository;

import com.example.module4_s11_restful_blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findAllByCategoryId(Long categoryId, Pageable pageable);
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
    Page<Blog> findAllByCategoryIdAndTitleContaining(Long categoryId, String title, Pageable pageable);
}
