package com.example.module4_s12_blogs_tich_hop_ajax.repository;

import com.example.module4_s12_blogs_tich_hop_ajax.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
