package com.example.module4_s5.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotEmpty(message = "Tên sản phẩm không được để trống")
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotNull(message = "Giá không được để trống")
    @Min(value = 0, message = "Giá phải lớn hơn hoặc bằng 0")
    @Column(name = "price", nullable = false)
    private double price;
    
    @NotEmpty(message = "Mô tả không được để trống")
    @Column(name = "description")
    private String description;
    
    @NotEmpty(message = "Hãng sản xuất không được để trống")
    @Column(name = "producer")
    private String producer;

    public Product() {
    }

    public Product(int id, String name, double price, String description, String producer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.producer = producer;
    }

    public Product(String name, double price, String description, String producer) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.producer = producer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
