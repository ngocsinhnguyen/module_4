package com.example.module4_s9_muonsach.service;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class BorrowCodeFileService {

    private static final String FILE = "borrow-codes.txt";

    public void saveBorrowCode(String borrowCode, Long bookId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
            writer.write(
                    LocalDateTime.now()
                            + " | BookID: " + bookId
                            + " | BorrowCode: " + borrowCode
            );
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Không thể ghi mã mượn sách");
        }
    }
}