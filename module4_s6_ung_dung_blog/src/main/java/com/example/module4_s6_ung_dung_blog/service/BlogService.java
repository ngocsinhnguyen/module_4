package com.example.module4_s6_ung_dung_blog.service;

import com.example.module4_s6_ung_dung_blog.entity.Blog;
import java.util.List;

public interface BlogService {
    List<Blog> findAll();
    Blog findById(Long id);
    Blog save(Blog blog);
    void deleteById(Long id);
}
