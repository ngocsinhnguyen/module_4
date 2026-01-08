package com.example.module4_s9_muonsach.repository;

import com.example.module4_s9_muonsach.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Optional<BorrowRecord> findByBorrowCode(String borrowCode);
}
