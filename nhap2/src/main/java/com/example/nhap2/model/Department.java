package com.example.nhap2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

/**
 * Entity đại diện cho bảng Phòng ban
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã phòng ban không được để trống")
    @Column(unique = true)
    private String code;

    @NotBlank(message = "Tên phòng ban không được để trống")
    private String name;

    // Quan hệ một-nhiều với Nhân viên
    // mappedBy trỏ đến tên biến 'department' trong class Employee
    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Employee> employees;
}
