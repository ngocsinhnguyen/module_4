package com.example.nhap2.controller;

import com.example.nhap2.model.Department;
import com.example.nhap2.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * Hiển thị danh sách tất cả phòng ban
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "department/list";
    }

    /**
     * Hiển thị form thêm mới phòng ban
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/form";
    }

    /**
     * Lưu thông tin phòng ban (cả thêm mới và cập nhật)
     * Sử dụng @Valid để kiểm tra tính hợp lệ của dữ liệu đầu vào
     */
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("department") Department department, BindingResult result) {
        // Nếu có lỗi validation, quay lại trang form
        if (result.hasErrors()) {
            return "department/form";
        }
        departmentService.save(department);
        return "redirect:/departments";
    }

    /**
     * Hiển thị form chỉnh sửa phòng ban dựa trên ID
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.findById(id));
        return "department/form";
    }

    /**
     * Xóa phòng ban theo ID
     * Nhận kết quả từ service để hiển thị thông báo thành công hoặc lỗi (nếu có nhân viên)
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = departmentService.delete(id);
        if (!deleted) {
            // Thêm thông báo lỗi nếu không thể xóa (do ràng buộc có nhân viên)
            redirectAttributes.addFlashAttribute("error", "Không thể xóa phòng ban đang có nhân viên!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Xóa phòng ban thành công!");
        }
        return "redirect:/departments";
    }
}
