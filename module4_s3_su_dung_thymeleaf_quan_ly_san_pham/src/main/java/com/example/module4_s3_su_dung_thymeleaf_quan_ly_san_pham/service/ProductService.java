package com.example.module4_s3_su_dung_thymeleaf_quan_ly_san_pham.service;

import com.example.module4_s3_su_dung_thymeleaf_quan_ly_san_pham.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private static final Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "iPhone 13", 15000000, "Apple iPhone 13", "Apple"));
        products.put(2, new Product(2, "Samsung Galaxy S22", 14000000, "Samsung Flagship", "Samsung"));
        products.put(3, new Product(3, "Xiaomi 12", 10000000, "Xiaomi Flagship", "Xiaomi"));
        products.put(4, new Product(4, "Oppo Reno 7", 8000000, "Oppo Mid-range", "Oppo"));
        products.put(5, new Product(5, "Vivo V23", 7500000, "Vivo Mid-range", "Vivo"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return products.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
