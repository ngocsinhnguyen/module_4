package com.example.module4_s3_su_dung_thymeleaf_quan_ly_san_pham.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handle404(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error/404");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler({org.springframework.beans.TypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handle400(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error/400");
        modelAndView.addObject("message", "Tham số không hợp lệ");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handle500(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error/500");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}
