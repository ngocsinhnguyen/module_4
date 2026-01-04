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
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("errorCode", "404");
        mav.addObject("errorTitle", "Trang không tồn tại");
        mav.addObject("errorMessage", "Rất tiếc, đường dẫn bạn truy cập không tìm thấy dữ liệu.");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handle500(Exception e) {
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("errorCode", "500");
        mav.addObject("errorTitle", "Lỗi hệ thống");
        mav.addObject("errorMessage", "Đã xảy ra lỗi không mong muốn. Chi tiết: " + e.getMessage());
        return mav;
    }
}
