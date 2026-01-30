package com.example.nhap21.service;

import com.example.nhap21.model.ProductOrder;
import com.example.nhap21.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ProductOrderService {
    @Autowired
    private ProductOrderRepository productOrderRepository;

    public List<ProductOrder> getAllOrders() {
        return productOrderRepository.findAll();
    }

    public List<ProductOrder> searchOrders(LocalDate startDate, LocalDate endDate, Boolean topTotal) {
        if (topTotal != null && topTotal) {
            ProductOrder maxOrder = productOrderRepository.findOrderWithMaxTotal();
            return maxOrder != null ? Collections.singletonList(maxOrder) : Collections.emptyList();
        }
        
        if (startDate != null && endDate != null) {
            return productOrderRepository.findByPurchaseDateBetween(startDate, endDate);
        }
        
        return productOrderRepository.findAll();
    }

    public void saveOrder(ProductOrder order) {
        productOrderRepository.save(order);
    }

    public ProductOrder getOrderById(Long id) {
        return productOrderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id) {
        productOrderRepository.deleteById(id);
    }
}
