package com.example.nhap1.controller;

import com.example.nhap1.model.Product;
import com.example.nhap1.model.ProductType;
import com.example.nhap1.repository.ProductTypeRepository;
import com.example.nhap1.service.ProductService;
import com.example.nhap1.service.ProductTypeService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * ProductController: Tầng điều hướng (Request Handling).
 * Tiếp nhận các yêu cầu HTTP từ trình duyệt và điều hướng tới trang HTML tương ứng.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    /**
     * @PostConstruct: Cháy ngay sau khi ứng dụng khởi động.
     * Dùng để khởi tạo dữ liệu mẫu cho Loại hàng hoá và một số Sản phẩm mẫu nếu DB đang trống.
     */
    @PostConstruct
    public void init() {
        if (productTypeRepository.count() == 0) {
            // Tạo các Loại hàng hoá
            ProductType dienthoai = new ProductType(null, "dienthoai", null);
            ProductType laptop = new ProductType(null, "Rau", null);
            ProductType rau = new ProductType(null, "Rau", null);
            ProductType cu = new ProductType(null, "Củ", null);
            ProductType qua = new ProductType(null, "Quả", null);
            ProductType hoa = new ProductType(null, "Hoa", null);
            productTypeRepository.saveAll(Arrays.asList(rau, cu, qua, hoa));

            // Thêm dữ liệu sản phẩm mẫu (Product)
            productService.save(new Product("MHH-0001", "Iphone X", "kg", 15000.0, rau));
            productService.save(new Product("MHH-0002", "Samsung Note 10", "kg", 25000.0, cu));
            productService.save(new Product("MHH-0003", "Dell Vostrol 5320", "túi", 85000.0, qua));
            productService.save(new Product("MHH-0004", "H", "bó", 45000.0, hoa));
            productService.save(new Product("MHH-0005", "Bắp Cải Trắng", "kg", 12000.0, rau));
        }
    }

    /**
     * Hiển thị danh sách hàng hoá.
     * @RequestParam: Lấy dữ liệu tìm kiếm từ URL/Form (nếu có).
     */
    @GetMapping("")
    public String list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Long typeId,
                       Model model) {
        List<Product> products;
        // Nếu có nhập tên hoặc chọn loại hàng thì thực hiện tìm kiếm
        if ((name != null && !name.isEmpty()) || typeId != null) {
            products = productService.search(name, typeId);
        } else {
            // Ngược lại hiển thị tất cả
            products = productService.findAll();
        }
        // thay đổi đề bài, xóa những phần cũ, table bây giờ gồm: stt, mà đơn hàng, tên sản phẩm, giá sản phấm, loại sản phẩm, ngày mua, số lượng sản phẩm, tổng tiền. chức năng lọc theo khoảng thời gian, và lọc theo đơn hàng có tổng tiền cao nhất
        // Gửi dữ liệu sang file Thymeleaf (list.html)
        model.addAttribute("products", products);
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("name", name); // Giữ lại giá trị tìm kiếm trên ô input
        model.addAttribute("typeId", typeId); // Giữ lại giá trị chọn trong dropdown
        return "product/list";
    }

    /**
     * Hiển thị form thêm mới sản phẩm.
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("productTypes", productTypeService.findAll());
        return "product/create";
    }

    /**
     * Xử lý lưu sản phẩm mới.
     * @Valid: Kích hoạt các quy tắc Validation đã đặt ở class Product.
     * BindingResult: Chứa danh sách các lỗi nếu dữ liệu nhập vào không hợp lệ.
     */
    @PostMapping("/create")
    public String save(@Valid @ModelAttribute("product") Product product,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        
        // Kiểm tra logic nghiệp vụ: Trùng mã hàng hoá
        if (product.getId() != null && productService.existsById(product.getId())) {
            result.rejectValue("id", "error.product", "Mã hàng hoá này đã tồn tại trong hệ thống!");
        }

        // Nếu có lỗi (lỗi định dạng hoặc lỗi trùng mã)
        if (result.hasErrors()) {
            model.addAttribute("productTypes", productTypeService.findAll());
            return "product/create"; // Quay lại trang tạo mới và hiển thị lỗi
        }
        
        productService.save(product);
        // Lưu thông báo thành công để hiển thị ở trang danh sách sau khi redirect
        redirectAttributes.addFlashAttribute("message", "Thêm mới hàng hoá thành công!");
        return "redirect:/products";
    }

    /**
     * Xử lý yêu cầu xoá sản phẩm.
     */
    @PostMapping("/delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
        productService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Xoá hàng hoá " + id + " thành công!");
        return "redirect:/products";
    }
}
