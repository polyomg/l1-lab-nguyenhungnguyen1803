package com.poly.lab8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home/index"})
    public String home(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến trang chủ!");
        return "home/index"; // Trả về view templates/home/index.html
    }

    @GetMapping("/admin/home/index")
    public String adminHome(Model model) {
        model.addAttribute("message", "Chào mừng Admin!");
        return "admin/home/index"; // Trả về templates/admin/home/index.html
    }
}
