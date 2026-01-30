package com.example.nhap2.service;

import com.example.nhap2.model.Department;
import com.example.nhap2.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Lấy toàn bộ danh sách phòng ban
     */
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    /**
     * Tìm phòng ban theo ID
     */
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    /**
     * Lưu hoặc cập nhật thông tin phòng ban
     */
    public void save(Department department) {
        departmentRepository.save(department);
    }

    /**
     * Xóa phòng ban. 
     * Yêu cầu: Không cho xóa phòng ban đang có nhân viên.
     * @return true nếu xóa thành công, false nếu phòng ban đang có nhân viên.
     */
    public boolean delete(Long id) {
        Department dept = findById(id);
        // Kiểm tra nếu phòng ban tồn tại và danh sách nhân viên rỗng mới cho xóa
        if (dept != null && (dept.getEmployees() == null || dept.getEmployees().isEmpty())) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
