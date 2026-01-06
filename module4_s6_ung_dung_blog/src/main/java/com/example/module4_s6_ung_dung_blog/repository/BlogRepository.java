package com.example.module4_s6_ung_dung_blog.repository;

import com.example.module4_s6_ung_dung_blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
