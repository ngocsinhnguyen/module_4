package com.example.nhap1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Thực thể (Entity) đại diện cho Sản phẩm (Hàng hoá) trong Database.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    /**
     * Mã hàng hoá đóng vai trò là Primary Key.
     * @NotBlank: Đảm bảo không được để trống.
     * @Pattern: Bắt buộc tuân thủ định dạng MHH-XXXX (X là số từ 0-9).
     */
    @NotBlank(message = "Mã hàng hoá không được để trống")
    @Pattern(regexp = "MHH-\\d{4}", message = "Mã hàng hoá phải đúng định dạng MHH-XXXX (với X là các số)")
    private String id;

    /**
     * Tên hàng hoá.
     */
    @NotBlank(message = "Tên hàng hoá không được để trống")
    private String name;

    /**
     * Đơn vị tính (kg, túi, bó, khay...).
     */
    @NotBlank(message = "Đơn vị tính không được để trống")
    private String unit;

    /**
     * Giá bán của sản phẩm.
     * @Min(1000): Giá thấp nhất phải là 1000 VNĐ theo yêu cầu đề bài.
     */
    @NotNull(message = "Giá không được để trống")
    @Min(value = 1000, message = "Giá phải là số nguyên dương và >= 1.000 VNĐ")
    private Double price;

    /**
     * Mối quan hệ Many-to-One với Loại hàng hoá.
     * Nhiều sản phẩm có thể thuộc cùng một Loại hàng.
     */
    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    @NotNull(message = "Vui lòng chọn loại hàng hoá")
    private ProductType productType;
}
