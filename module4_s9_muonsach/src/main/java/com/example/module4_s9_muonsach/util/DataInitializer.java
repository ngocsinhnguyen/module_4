package com.example.module4_s9_muonsach.util;

import com.example.module4_s9_muonsach.entity.Book;
import com.example.module4_s9_muonsach.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            bookRepository.save(new Book(null, "Clean Code", 5));
            bookRepository.save(new Book(null, "Design Patterns", 3));
            bookRepository.save(new Book(null, "Effective Java", 1));
            bookRepository.save(new Book(null, "Refactoring", 0));
        }
    }
}
