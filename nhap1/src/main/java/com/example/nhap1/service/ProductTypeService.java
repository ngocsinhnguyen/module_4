package com.example.nhap1.service;

import com.example.nhap1.model.ProductType;
import com.example.nhap1.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductType> findAll() {
        return productTypeRepository.findAll();
    }
}
