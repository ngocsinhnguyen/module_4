package com.example.module4_s11_restful_blog.service;

import com.example.module4_s11_restful_blog.entity.Blog;
import com.example.module4_s11_restful_blog.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findByCategoryId(Long categoryId, Pageable pageable) {
        return blogRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Blog> findAllWithSearch(String title, Pageable pageable) {
        return blogRepository.findAllByTitleContaining(title, pageable);
    }

    @Override
    public Page<Blog> findByCategoryIdWithSearch(Long categoryId, String title, Pageable pageable) {
        return blogRepository.findAllByCategoryIdAndTitleContaining(categoryId, title, pageable);
    }
}
