package com.example.module4_s8_validate_thongtinbaihat.controller;

import com.example.module4_s8_validate_thongtinbaihat.dto.SongDTO;
import com.example.module4_s8_validate_thongtinbaihat.services.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping("")
    public String list(Model model) {
        List<SongDTO> songs = songService.findAll();
        model.addAttribute("songs", songs);
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("songDTO", new SongDTO());
        return "form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        SongDTO songDTO = songService.findById(id);
        if (songDTO == null) {
            return "redirect:/songs";
        }
        model.addAttribute("songDTO", songDTO);
        return "form";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute("songDTO") SongDTO songDTO, 
                       BindingResult bindingResult, 
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        songService.save(songDTO);
        redirectAttributes.addFlashAttribute("message", "Lưu bài hát thành công!");
        return "redirect:/songs";
    }
}
