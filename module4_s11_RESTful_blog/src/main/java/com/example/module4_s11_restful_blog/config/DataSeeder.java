package com.example.module4_s11_restful_blog.config;

import com.example.module4_s11_restful_blog.entity.Category;
import com.example.module4_s11_restful_blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            List<Category> categories = Arrays.asList(
                    new Category(null, "Sức khỏe", null),
                    new Category(null, "Thể thao", null),
                    new Category(null, "Khoa học", null),
                    new Category(null, "Lịch sử", null)
            );
            categoryRepository.saveAll(categories);
        }
    }
}
