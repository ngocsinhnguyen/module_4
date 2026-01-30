package com.example.nhap21.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller xử lý các yêu cầu điều hướng trang chủ.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/orders";
    }
}
