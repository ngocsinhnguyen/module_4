package com.example.module4_s1_tu_dien.controller;

import com.example.module4_s1_tu_dien.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("word") String word, Model model) {
        String vietnamese = dictionaryService.findWord(word);
        
        model.addAttribute("searchWord", word);
        
        if (vietnamese != null) {
            model.addAttribute("result", vietnamese);
        } else {
            model.addAttribute("notFound", true);
        }
        
        return "index";
    }
}
