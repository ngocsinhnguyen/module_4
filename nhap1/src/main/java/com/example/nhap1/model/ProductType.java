package com.example.nhap1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Thực thể đại diện cho loại hàng hoá (Rau, Củ, Quả, Hoa)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "productType")
    private List<Product> products;
}
