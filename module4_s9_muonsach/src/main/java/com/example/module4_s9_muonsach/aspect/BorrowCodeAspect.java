package com.example.module4_s9_muonsach.aspect;

import com.example.module4_s9_muonsach.service.BorrowCodeFileService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class BorrowCodeAspect {

    private final BorrowCodeFileService borrowCodeFileService;

    @AfterReturning(
            pointcut = "execution(* com.example.module4_s9_muonsach.service.BookService.borrowBook(..))",
            returning = "returnValue"
    )
    public void saveBorrowCodeToFile(JoinPoint joinPoint, Object returnValue) {
        Long bookId = (Long) joinPoint.getArgs()[0];

        String borrowCode = (String) returnValue;

        borrowCodeFileService.saveBorrowCode(borrowCode, bookId);
    }
}