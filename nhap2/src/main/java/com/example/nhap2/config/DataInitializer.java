package com.example.nhap2.config;

import com.example.nhap2.model.Department;
import com.example.nhap2.model.Employee;
import com.example.nhap2.repository.DepartmentRepository;
import com.example.nhap2.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/**
 * Lớp khởi tạo dữ liệu mẫu khi ứng dụng khởi chạy
 */
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(DepartmentRepository departmentRepository, 
                                      EmployeeRepository employeeRepository) {
        return args -> {
            // Khởi tạo một số phòng ban mẫu
            Department hr = new Department(null, "PB-HR", "Hành chính Nhân sự", null);
            Department it = new Department(null, "PB-IT", "Công nghệ thông tin", null);
            Department sale = new Department(null, "PB-SALE", "Kinh doanh", null);

            departmentRepository.save(hr);
            departmentRepository.save(it);
            departmentRepository.save(sale);

            // Khởi tạo một số nhân viên mẫu
            employeeRepository.save(new Employee(null, "NV-0001", "Nguyễn Văn A", 
                LocalDate.of(1995, 5, 15), "Nam", 15000000.0, hr));
            
            employeeRepository.save(new Employee(null, "NV-0002", "Trần Thị B", 
                LocalDate.of(2000, 10, 20), "Nữ", 12000000.0, it));
                
            employeeRepository.save(new Employee(null, "NV-0003", "Lê Văn C", 
                LocalDate.of(1990, 1, 1), "Nam", 20000000.0, sale));
        };
    }
}
