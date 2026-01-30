package com.example.nhap2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

/**
 * Entity đại diện cho bảng Nhân viên trong cơ sở dữ liệu
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mã nhân viên phải bắt đầu bằng NV- và theo sau là 4 chữ số
    @NotBlank(message = "Mã nhân viên là bắt buộc")
    @Pattern(regexp = "NV-\\d{4}", message = "Mã nhân viên phải có định dạng NV-XXXX (với X là số)")
    @Column(unique = true)
    private String employeeCode;

    @NotBlank(message = "Họ tên là bắt buộc")
    private String fullName;

    // Ngày sinh dùng để tính tuổi (phải >= 18)
    @NotNull(message = "Ngày sinh là bắt buộc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotBlank(message = "Giới tính là bắt buộc")
    private String gender;

    // Lương cơ bản phải là số dương và ít nhất 5.000.000
    @NotNull(message = "Lương cơ bản là bắt buộc")
    @Min(value = 5000000, message = "Lương cơ bản phải ít nhất là 5.000.000")
    private Double salary;

    // Mỗi nhân viên thuộc về một phòng ban
    @ManyToOne
    @JoinColumn(name = "department_id")
    @NotNull(message = "Phòng ban là bắt buộc")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;

    /**
     * Phương thức kiểm tra tuổi nhân viên có đủ 18 hay không
     * Sử dụng @AssertTrue để Hibernate Validator tự động gọi khi validate
     */
    @AssertTrue(message = "Nhân viên phải từ 18 tuổi trở lên")
    public boolean isAgeValid() {
        if (dob == null) return true; // Để @NotNull xử lý trường hợp null
        return Period.between(dob, LocalDate.now()).getYears() >= 18;
    }
}
