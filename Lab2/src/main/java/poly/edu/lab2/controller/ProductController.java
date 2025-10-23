package poly.edu.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.lab2.model.Product;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    // dsach sp lưu sp
    private final List<Product> items = new ArrayList<>();

    // form mđịnh
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("defaultProduct", new Product("iPhone 30", 5000.0));
        return "product_form";
    }

    // save sp
    @PostMapping("/save")
    public String save(@ModelAttribute("savedProduct") Product p, Model model) {
        // luôn hiển thị sp vừa lưu
        model.addAttribute("savedProduct", p);

        // sp mđịnh
        model.addAttribute("defaultProduct", new Product("iPhone 30", 5000.0));

        // thêm sp
        items.add(new Product(p.getName(), p.getPrice()));

        return "product_form";
    }

    //dsach sp thymeleaf ?3
    @ModelAttribute("items")
    public List<Product> getItems() {
        return items;
    }
}
