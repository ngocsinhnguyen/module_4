package com.example.module4_s12_blogs_tich_hop_ajax.controller.api;

import com.example.module4_s12_blogs_tich_hop_ajax.entity.Blog;
import com.example.module4_s12_blogs_tich_hop_ajax.service.IBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogRestController {

    private final IBlogService blogService;


    @GetMapping
    public ResponseEntity<Page<Blog>> getAllBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sort) {
        
        Sort.Direction direction = "asc".equalsIgnoreCase(sort) 
            ? Sort.Direction.ASC 
            : Sort.Direction.DESC;
        
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, "createdAt"));
        Page<Blog> blogs = blogService.findAll(pageRequest);
        
        return ResponseEntity.ok(blogs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Blog not found with id: " + id));
        return ResponseEntity.ok(blog);
    }


    @GetMapping("/{categoryId}/category/")
    public ResponseEntity<Page<Blog>> getBlogsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sort) {
        
        Sort.Direction direction = "asc".equalsIgnoreCase(sort) 
            ? Sort.Direction.ASC 
            : Sort.Direction.DESC;
        
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, "createdAt"));
        Page<Blog> blogs = blogService.findByCategoryId(categoryId, pageRequest);
        
        return ResponseEntity.ok(blogs);
    }
}
