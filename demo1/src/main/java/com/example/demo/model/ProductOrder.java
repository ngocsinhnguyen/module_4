package com.example.demo.model;

package com.example.nhap21.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "product_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Mã đơn hàng không được để trống")
    @Pattern(regexp = "^DH-\\d{4}$", message = "Mã đơn hàng phải theo định dạng DH-XXXX")
    private String orderCode;

    @NotEmpty(message = "Tên sản phẩm không được để trống")
    private String productName;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1, message = "Giá sản phẩm phải lớn hơn 0")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Loại sản phẩm không được để trống")
    private Category category;

    @NotNull(message = "Ngày mua không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải ít nhất là 1")
    private Integer quantity;

    @Transient
    public Double getTotalAmount() {
        if (price == null || quantity == null) return 0.0;
        return price * quantity;
    }
}
