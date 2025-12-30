package com.example.module4_s3_su_dung_thymeleaf_quan_ly_san_pham.service;

import com.example.module4_s3_su_dung_thymeleaf_quan_ly_san_pham.model.Product;
import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(int id);

    void update(int id, Product product);

    void remove(int id);

    List<Product> findByName(String name);
}
