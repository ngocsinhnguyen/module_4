package com.example.module4_s12_blogs_tich_hop_ajax.repository;

import com.example.module4_s12_blogs_tich_hop_ajax.entity.Blog;
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
