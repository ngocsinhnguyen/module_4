package com.example.nhap21.repository;

import com.example.nhap21.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    
    @Query("SELECT o FROM ProductOrder o WHERE o.purchaseDate BETWEEN :startDate AND :endDate")
    List<ProductOrder> findByPurchaseDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT * FROM product_orders ORDER BY (price * quantity) DESC LIMIT 1", nativeQuery = true)
    ProductOrder findOrderWithMaxTotal();

    @Query("SELECT o FROM ProductOrder o ORDER BY (o.price * o.quantity) DESC")
    List<ProductOrder> findAllOrderByTotalDesc();
}
