package com.example.nhap21.config;

import com.example.nhap21.model.Category;
import com.example.nhap21.model.ProductOrder;
import com.example.nhap21.repository.CategoryRepository;
import com.example.nhap21.repository.ProductOrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepo, ProductOrderRepository orderRepo) {
        return args -> {

            if (categoryRepo.count() == 0) {
                categoryRepo.saveAll(Arrays.asList(
                    new Category("Điện thoại"),
                    new Category("Máy tính")
                ));
            }


            if (orderRepo.count() == 0) {
                List<Category> categories = categoryRepo.findAll();
                Category phone = categories.stream().filter(c -> c.getName().equals("Điện thoại")).findFirst().orElse(null);
                Category laptop = categories.stream().filter(c -> c.getName().equals("Máy tính")).findFirst().orElse(null);

                orderRepo.saveAll(Arrays.asList(
                    new ProductOrder(null, "DH-0001", "iPhone X", 1000.0, phone, LocalDate.of(2020, 3, 21), 1),
                    new ProductOrder(null, "DH-0002", "Dell 5320", 400.0, laptop, LocalDate.of(2019, 1, 11), 3),
                    new ProductOrder(null, "DH-0003", "Samsung Note 10", 1200.0, phone, LocalDate.of(2020, 2, 6), 2),
                    new ProductOrder(null, "DH-0005", "Acer 5320", 600.0, laptop, LocalDate.of(2018, 3, 1), 1),
                    new ProductOrder(null, "DH-0006", "Oppo F10", 350.0, phone, LocalDate.of(2020, 3, 21), 1)
                ));
            }
        };
    }
}
