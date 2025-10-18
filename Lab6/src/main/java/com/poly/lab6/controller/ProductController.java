package com.poly.lab6.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.lab6.dao.ProductDAO;
import com.poly.lab6.entity.Product;

@Controller
public class ProductController {

    @Autowired
    ProductDAO dao;

    // ================= BÀI 3: SORTING =================
    @RequestMapping("/product/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {
        // Nếu chưa chọn cột -> mặc định sắp theo "price"
        String sortField = field.orElse("price");

        // Sắp xếp giảm dần theo cột được chọn
        Sort sort = Sort.by(Direction.DESC, sortField);

        // Lấy danh sách sản phẩm đã sắp xếp
        List<Product> items = dao.findAll(sort);

        // Truyền dữ liệu ra view
        model.addAttribute("field", sortField.toUpperCase());
        model.addAttribute("items", items);

        return "product/sort"; // Gọi tới file templates/product/sort.html
    }

    // ================= BÀI 4: PAGINATION =================
    @RequestMapping("/product/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        // Trang hiện tại (mặc định 0)
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);

        model.addAttribute("page", page);

        return "product/page"; // Gọi tới file templates/product/page.html
    }
}
