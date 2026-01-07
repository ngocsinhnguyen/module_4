package com.example.module4_s6_blog.controller;

import com.example.module4_s6_blog.entity.Blog;
import com.example.module4_s6_blog.entity.Category;
import com.example.module4_s6_blog.service.IBlogService;
import com.example.module4_s6_blog.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final IBlogService blogService;
    private final ICategoryService categoryService;

    public BlogController(IBlogService blogService, ICategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("authors")
    public List<String> getAuthors() {
        return List.of("Admin", "Editor", "Guest", "Ngoc Sinh");
    }

    @GetMapping
    public String listBlogs(@RequestParam(required = false) Long categoryId,
                            @RequestParam(defaultValue = "0") int page,
                            Model model) {
        Page<Blog> blogPage;
        if (categoryId != null) {
            blogPage = blogService.findByCategoryId(categoryId, PageRequest.of(page, 2));
            model.addAttribute("selectedCategoryId", categoryId);
        } else {
            blogPage = blogService.findAll(PageRequest.of(page, 2));
        }
        model.addAttribute("blogPage", blogPage);
        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("currentPage", page);
        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @PostMapping("/create")
    public String createBlog(@Valid @ModelAttribute("blog") Blog blog, 
                             BindingResult bindingResult, 
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "blog/create";
        }
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Blog created successfully!");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
        model.addAttribute("blog", blog);
        return "blog/view";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
        model.addAttribute("blog", blog);
        return "blog/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBlog(@PathVariable Long id, 
                             @Valid @ModelAttribute("blog") Blog blog, 
                             BindingResult bindingResult, 
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "blog/edit";
        }
        blog.setId(id);
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Blog updated successfully!");
        return "redirect:/blogs";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Blog deleted successfully!");
        return "redirect:/blogs";
    }
}
