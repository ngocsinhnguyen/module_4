package com.example.module4_s5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/400")
    public ModelAndView handle400() {
        return createErrorView("400", "Yêu cầu không hợp lệ", "Dữ liệu bạn gửi đi không đúng định dạng hoặc bị thiếu.");
    }

    @GetMapping("/404")
    public ModelAndView handle404() {
        return createErrorView("404", "Trang không tồn tại", "Rất tiếc, đường dẫn bạn truy cập không tồn tại hoặc đã bị gỡ bỏ.");
    }

    @GetMapping("/500")
    public ModelAndView handle500() {
        return createErrorView("500", "Lỗi máy chủ", "Hệ thống đang gặp sự cố kỹ thuật. Vui lòng thử lại sau.");
    }

    private ModelAndView createErrorView(String code, String title, String message) {
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("errorCode", code);
        mav.addObject("errorTitle", title);
        mav.addObject("errorMessage", message);
        return mav;
    }
}
