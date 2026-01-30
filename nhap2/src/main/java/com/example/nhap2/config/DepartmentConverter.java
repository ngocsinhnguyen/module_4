package com.example.nhap2.config;

import com.example.nhap2.model.Department;
import com.example.nhap2.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter giúp Spring chuyển đổi từ ID (String/Long) sang đối tượng Department
 * Điều này cần thiết khi binding dữ liệu trong thẻ <select> của Thymeleaf
 */
@Component
public class DepartmentConverter implements Converter<String, Department> {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Department convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.parseLong(source);
            return departmentService.findById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
