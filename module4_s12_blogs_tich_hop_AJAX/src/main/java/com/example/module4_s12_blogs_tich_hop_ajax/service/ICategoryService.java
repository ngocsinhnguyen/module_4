package com.example.module4_s12_blogs_tich_hop_ajax.service;

import com.example.module4_s12_blogs_tich_hop_ajax.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    void deleteById(Long id);
}
