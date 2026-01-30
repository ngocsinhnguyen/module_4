package com.example.nhap2.service;

import com.example.nhap2.model.Employee;
import com.example.nhap2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Lấy danh sách tất cả nhân viên
     */
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * Tìm kiếm nhân viên theo tên và phòng ban
     */
    public List<Employee> search(String name, Long deptId) {
        return employeeRepository.search(name, deptId);
    }

    /**
     * Tìm nhân viên theo ID
     */
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    /**
     * Lưu hoặc cập nhật nhân viên
     */
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    /**
     * Xóa nhân viên
     */
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
