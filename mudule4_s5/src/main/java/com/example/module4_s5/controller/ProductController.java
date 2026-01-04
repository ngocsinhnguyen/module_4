package com.example.module4_s5.controller;

import com.example.module4_s5.entity.Product;
import com.example.module4_s5.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String listProducts(@RequestParam(value = "searchName", required = false) String searchName,
                               @RequestParam(value = "minPrice", required = false) Double minPrice,
                               @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                               Model model) {
        List<Product> products;
        if ((searchName != null && !searchName.isEmpty()) || minPrice != null || maxPrice != null) {
            products = productService.search(searchName, minPrice, maxPrice);
        } else {
            products = productService.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("searchName", searchName);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "product/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "product/create";
        }
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Thêm mới sản phẩm thành công!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "product/edit";
        }
        productService.update(product);
        redirectAttributes.addFlashAttribute("message", "Cập nhật sản phẩm thành công!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        productService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Xóa sản phẩm thành công!");
        return "redirect:/products";
    }
}
