package com.example.module4_s9_muonsach.controller;

import com.example.module4_s9_muonsach.entity.Book;
import com.example.module4_s9_muonsach.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        return "detail";
    }

    @PostMapping("/borrow")
    public String borrow(@RequestParam Long bookId, RedirectAttributes redirectAttributes) throws Exception {
        String borrowCode = bookService.borrowBook(bookId);
        redirectAttributes.addFlashAttribute("message", "Mượn sách thành công. Mã số mượn sách của bạn là: " + borrowCode);
        return "redirect:/books";
    }

    @GetMapping("/return")
    public String returnForm() {
        return "return";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String borrowCode, RedirectAttributes redirectAttributes) throws Exception {
        bookService.returnBook(borrowCode);
        redirectAttributes.addFlashAttribute("message", "Trả sách thành công!");
        return "redirect:/books";
    }
}
