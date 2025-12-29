package com.example.module4_s2_may_tinh_ca_nhan.controller;

import com.example.module4_s2_may_tinh_ca_nhan.model.Calculator;
import com.example.module4_s2_may_tinh_ca_nhan.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/")
    public String showCalculator(Model model) {
        model.addAttribute("calculator", new Calculator());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@ModelAttribute("calculator") Calculator calculator, Model model) {
        calculatorService.calculate(calculator);
        return "index";
    }
}
