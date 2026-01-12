package com.example.module4_s11_restful_blog.controller;

import com.example.module4_s11_restful_blog.entity.Blog;
import com.example.module4_s11_restful_blog.entity.Category;
import com.example.module4_s11_restful_blog.service.IBlogService;
import com.example.module4_s11_restful_blog.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
                            @RequestParam(required = false) String search,
                            @RequestParam(defaultValue = "desc") String sort,
                            @RequestParam(defaultValue = "0") int page,
                            Model model) {
        Page<Blog> blogPage;
        Sort.Direction direction = "asc".equalsIgnoreCase(sort) ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page, 2, Sort.by(direction, "createdAt"));

        if (categoryId != null && search != null && !search.isEmpty()) {
            blogPage = blogService.findByCategoryIdWithSearch(categoryId, search, pageRequest);
            model.addAttribute("selectedCategoryId", categoryId);
            model.addAttribute("search", search);
        } else if (categoryId != null) {
            blogPage = blogService.findByCategoryId(categoryId, pageRequest);
            model.addAttribute("selectedCategoryId", categoryId);
        } else if (search != null && !search.isEmpty()) {
            blogPage = blogService.findAllWithSearch(search, pageRequest);
            model.addAttribute("search", search);
        } else {
            blogPage = blogService.findAll(pageRequest);
        }

        model.addAttribute("blogPage", blogPage);
        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("sort", sort);
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

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
        model.addAttribute("blog", blog);
        return "blog/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateBlog(@PathVariable Long id, 
                             @Valid @ModelAttribute("blog") Blog blog, 
                             BindingResult bindingResult, 
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "blog/edit";
        }
        blogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
        blog.setId(id);
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Blog updated successfully!");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
        blogService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Blog deleted successfully!");
        return "redirect:/blogs";
    }
}
