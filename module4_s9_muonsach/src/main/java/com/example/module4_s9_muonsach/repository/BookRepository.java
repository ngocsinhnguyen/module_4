package com.example.module4_s9_muonsach.repository;

import com.example.module4_s9_muonsach.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
