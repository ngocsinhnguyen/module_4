package com.example.module_s10_giohang.service;

import com.example.module_s10_giohang.entity.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();

    Optional<Product> findById(Long id);
}