package com.example.module4_s9_muonsach.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class LibraryAspect {
    private static final String LOG_FILE = "library.log";
    private static final AtomicInteger visitorCount = new AtomicInteger(0);

    @Pointcut("within(com.example.module4_s9_muonsach.controller.BookController)")
    public void anyControllerAction() {}

    @Pointcut("execution(* com.example.module4_s9_muonsach.service.BookService.borrowBook(..)) || " +
              "execution(* com.example.module4_s9_muonsach.service.BookService.returnBook(..))")
    public void bookStatusChangeAction() {}

    @Before("anyControllerAction()")
    public void countVisitor(JoinPoint joinPoint) {
        int currentCount = visitorCount.incrementAndGet();
        writeLog("SỐ LƯỢT TRUY CẬP: " + currentCount + " | Thao tác: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("bookStatusChangeAction()")
    public void logStatusChange(JoinPoint joinPoint) {
        String action = joinPoint.getSignature().getName();
        writeLog("THAY ĐỔI TRẠNG THÁI: " + (action.equals("borrowBook") ? "Mượn sách" : "Trả sách") + " | " + joinPoint.getSignature().toShortString());
    }

    private void writeLog(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(LocalDateTime.now() + " - " + message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Không thể ghi log vào file: " + e.getMessage());
        }
    }
}
