package com.example.chuyen_doi_tien_te.controller;

import com.example.chuyen_doi_tien_te.exception.InvalidAmountException;
import com.example.chuyen_doi_tien_te.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConverterController {

    @Autowired
    private ConverterService converterService;

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam("rate") double rate, 
                          @RequestParam("usd") double usd, 
                          Model model) throws InvalidAmountException {

        double vnd = converterService.convertUsdToVnd(rate, usd);
        
        model.addAttribute("rate", rate);
        model.addAttribute("usd", usd);
        model.addAttribute("vnd", vnd);
        
        return "result";
    }

    @ExceptionHandler(InvalidAmountException.class)
    public String handleInvalidAmount(InvalidAmountException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
