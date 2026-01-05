package com.example.module4_s5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handle404() {
        return createErrorView("404", "Trang không tồn tại", "Rất tiếc, đường dẫn bạn truy cập không tìm thấy dữ liệu.");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handle500(Exception e) {
        return createErrorView("500", "Lỗi hệ thống", "Đã xảy ra lỗi không mong muốn. Chi tiết: " + e.getMessage());
    }

    private ModelAndView createErrorView(String code, String title, String message) {
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("errorCode", code);
        mav.addObject("errorTitle", title);
        mav.addObject("errorMessage", message);
        return mav;
    }
}
