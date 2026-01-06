package com.example.module4_s6_ung_dung_blog.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Nhập chữ thay vì số trong URL
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ModelAndView handleTypeMismatch() {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("error", "ID blog phải là số");
        return mav;
    }

    // Blog không tồn tại
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgument(Exception ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("error", ex.getMessage());
        return mav;
    }
}
