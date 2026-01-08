package com.example.module4_s9_muonsach.service;

import com.example.module4_s9_muonsach.entity.Book;
import com.example.module4_s9_muonsach.entity.BorrowRecord;
import com.example.module4_s9_muonsach.repository.BookRepository;
import com.example.module4_s9_muonsach.repository.BorrowRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public String borrowBook(Long bookId) throws Exception {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new Exception("Sách không tồn tại"));
        if (book.getQuantity() <= 0) {
            throw new Exception("Số lượng sách đã hết, không thể mượn");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        String borrowCode = String.format("%05d", new Random().nextInt(100000));
        BorrowRecord record = new BorrowRecord();
        record.setBookId(bookId);
        record.setBorrowCode(borrowCode);
        borrowRecordRepository.save(record);

        return borrowCode;
    }

    @Transactional
    public void returnBook(String borrowCode) throws Exception {
        BorrowRecord record = borrowRecordRepository.findByBorrowCode(borrowCode)
                .orElseThrow(() -> new Exception("Mã số mượn sách không hợp lệ"));

        Book book = bookRepository.findById(record.getBookId())
                .orElseThrow(() -> new Exception("Sách không tồn tại"));

        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        borrowRecordRepository.delete(record);
    }
}
