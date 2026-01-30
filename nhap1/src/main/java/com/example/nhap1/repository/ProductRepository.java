package com.example.nhap1.repository;

import com.example.nhap1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * ProductRepository: Tầng giao tiếp với Cơ sở dữ liệu (Database).
 * Kế thừa JpaRepository để sử dụng các phương thức CRUD cơ bản (Save, Find, Delete...).
 */
public interface ProductRepository extends JpaRepository<Product, String> {
    
    /**
     * Truy vấn tìm kiếm hàng hoá theo Tên và Loại hàng hoá.
     * Sử dụng JPQL (Java Persistence Query Language).
     * 
     * Giải thích logic:
     * - :name IS NULL OR ... : Nếu tham số name truyền vào là null, điều kiện này sẽ luôn đúng (bỏ qua lọc theo tên).
     * - LOWER(p.name) LIKE LOWER(...) : Tìm kiếm tên không phân biệt chữ hoa hay chữ thường.
     * - CONCAT('%', :name, '%') : Tìm kiếm theo kiểu "chứa ký tự", tương đương với *tên* trong SQL.
     * - :typeId IS NULL OR p.productType.id = :typeId : Tương tự, nếu typeId là null thì lấy tất cả các loại.
     */
    @Query("SELECT p FROM Product p WHERE " +
           "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:typeId IS NULL OR p.productType.id = :typeId)")
    List<Product> searchProducts(@Param("name") String name, @Param("typeId") Long typeId);
}
