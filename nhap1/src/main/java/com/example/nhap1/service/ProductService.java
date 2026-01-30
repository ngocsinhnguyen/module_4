package com.example.nhap1.service;

import com.example.nhap1.model.Product;
import com.example.nhap1.model.ProductType;
import com.example.nhap1.repository.ProductRepository;
import com.example.nhap1.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductService: Tầng xử lý nghiệp vụ (Business Logic).
 * Đứng giữa Controller và Repository để xử lý dữ liệu trước khi lưu hoặc sau khi lấy ra.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Lấy danh sách tất cả hàng hoá từ DB.
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Tìm kiếm hàng hoá dựa trên tên và mã loại hàng.
     */
    public List<Product> search(String name, Long typeId) {
        return productRepository.searchProducts(name, typeId);
    }

    /**
     * Lưu thông tin hàng hoá (Thêm mới hoặc Cập nhật).
     */
    public void save(Product product) {
        productRepository.save(product);
    }

    /**
     * Xoá hàng hoá dựa trên Mã hàng (ID).
     */
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    /**
     * Kiểm tra xem Mã hàng đã tồn tại trong hệ thống chưa.
     */
    public boolean existsById(String id) {
        return productRepository.existsById(id);
    }
}
