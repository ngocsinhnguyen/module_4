package com.example.nhap2.controller;

import com.example.nhap2.model.Employee;
import com.example.nhap2.service.DepartmentService;
import com.example.nhap2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * Hiển thị danh sách nhân viên kết hợp tìm kiếm bộ lọc
     * @param name Tên nhân viên cần tìm (không bắt buộc)
     * @param deptId ID phòng ban cần lọc (không bắt buộc)
     */
    @GetMapping
    public String list(Model model, 
                       @RequestParam(required = false) String name, 
                       @RequestParam(required = false) Long deptId) {
        // Tìm kiếm nhân viên và truyền dữ liệu cần thiết ra view
        model.addAttribute("employees", employeeService.search(name, deptId));
        model.addAttribute("departments", departmentService.findAll()); // Để hiển thị danh sách trong select lọc
        model.addAttribute("name", name);
        model.addAttribute("deptId", deptId);
        return "employee/list";
    }

    /**
     * Hiển thị form thêm nhân viên mới
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.findAll());
        return "employee/form";
    }

    /**
     * Lưu thông tin nhân viên
     * @Valid kích hoạt kiểm tra dữ liệu theo các annotation trong Entity (tuổi >= 18, lương >= 5tr, ...)
     */
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        // Nếu dữ liệu nhập vào không hợp lệ
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll()); // Load lại list phòng ban cho dropdown
            return "employee/form";
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }

    /**
     * Hiển thị form chỉnh sửa thông tin nhân viên
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        model.addAttribute("departments", departmentService.findAll());
        return "employee/form";
    }

    /**
     * Xóa nhân viên theo ID
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
