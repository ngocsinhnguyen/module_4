package com.example.module4_s5.repository;

import com.example.module4_s5.entity.Product;
import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
    void update(Product product);
    void delete(int id);
    List<Product> search(String name, Double minPrice, Double maxPrice);
}
