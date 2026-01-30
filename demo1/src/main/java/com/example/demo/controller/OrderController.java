package com.example.demo.controller;


import com.example.demo.model.ProductOrder;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private ProductOrderService orderService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listOrders(Model model,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                             @RequestParam(required = false) Boolean topTotal) {
        model.addAttribute("orders", orderService.searchOrders(startDate, endDate, topTotal));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("topTotal", topTotal);
        return "order/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new ProductOrder());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "order/form";
    }

    @PostMapping("/save")
    public String saveOrder(@Valid @ModelAttribute("order") ProductOrder order,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "order/form";
        }
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProductOrder order = orderService.getOrderById(id);
        if (order == null) {
            return "redirect:/orders";
        }
        model.addAttribute("order", order);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "order/form";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}

