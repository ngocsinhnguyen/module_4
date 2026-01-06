package com.example.module4_s6_ung_dung_blog.controller;

import com.example.module4_s6_ung_dung_blog.entity.Blog;
import com.example.module4_s6_ung_dung_blog.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /* @ModelAttribute dùng chung cho form */
    @ModelAttribute("blog")
    public Blog initBlog() {
        return new Blog();
    }

    // Hiển thị danh sách blog
    @GetMapping
    public String listBlogs(Model model) {
        model.addAttribute("blogs", blogService.findAll());
        return "blog/list";
    }

    // Hiển thị form tạo blog
    @GetMapping("/create")
    public String showCreateForm() {
        return "blog/create";
    }

    // Tạo blog mới (BindingResult)
    @PostMapping("/create")
    public String createBlog(
            @Valid @ModelAttribute("blog") Blog blog,
            BindingResult result) {

        if (result.hasErrors()) {
            return "blog/create";
        }

        blogService.save(blog);
        return "redirect:/blogs";
    }

    // Xem chi tiết blog
    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        return "blog/view";
    }

    // Hiển thị form sửa blog
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        return "blog/edit";
    }

    // Cập nhật blog
    @PostMapping("/edit")
    public String updateBlog(
            @Valid @ModelAttribute("blog") Blog blog,
            BindingResult result) {

        if (result.hasErrors()) {
            return "blog/edit";
        }

        blogService.save(blog);
        return "redirect:/blogs";
    }

    // Xóa blog
    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteById(id);
        return "redirect:/blogs";
    }
}
