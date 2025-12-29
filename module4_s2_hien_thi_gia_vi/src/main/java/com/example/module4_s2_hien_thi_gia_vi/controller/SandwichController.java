package com.example.module4_s2_hien_thi_gia_vi.controller;

import com.example.module4_s2_hien_thi_gia_vi.model.Sandwich;
import com.example.module4_s2_hien_thi_gia_vi.service.CondimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SandwichController {

    @Autowired
    private CondimentService condimentService;

    @GetMapping("/")
    public String showForm(Model model) {
        List<String> condiments = condimentService.getAllCondiments();
        model.addAttribute("condiments", condiments);
        return "index";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "condiment", required = false) String[] condiments, Model model) {
        Sandwich sandwich = new Sandwich(condiments);
        model.addAttribute("sandwich", sandwich);
        return "result";
    }
}
