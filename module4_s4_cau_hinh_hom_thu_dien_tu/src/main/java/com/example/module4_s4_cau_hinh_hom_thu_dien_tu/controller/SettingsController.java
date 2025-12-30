package com.example.module4_s4_cau_hinh_hom_thu_dien_tu.controller;

import com.example.module4_s4_cau_hinh_hom_thu_dien_tu.model.MailSettings;
import com.example.module4_s4_cau_hinh_hom_thu_dien_tu.service.SettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsService service;

    public SettingsController(SettingsService service) {
        this.service = service;
    }

    @GetMapping
    public String showForm(Model model) {
        MailSettings settings = service.findSettings();
        model.addAttribute("settings", settings);
        model.addAttribute("languages", new String[]{"English", "Vietnamese", "Japanese", "Chinese"});
        model.addAttribute("pageSizes", new int[]{5, 10, 15, 25, 50, 100});
        return "settings";
    }

    @PostMapping("/update")
    public String updateSettings(@ModelAttribute("settings") MailSettings settings, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("languages", new String[]{"English", "Vietnamese", "Japanese", "Chinese"});
            model.addAttribute("pageSizes", new int[]{5, 10, 15, 25, 50, 100});
            return "settings";
        }
        service.saveSettings(settings);
        return "redirect:/settings";
    }
}
