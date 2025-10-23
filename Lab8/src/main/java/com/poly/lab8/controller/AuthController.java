package com.poly.lab8.controller;

import com.poly.lab8.entity.Account;
import com.poly.lab8.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession session;

    @GetMapping("/auth/login")
    public String loginForm(Model model) {
        return "/auth/login";
    }

    @PostMapping("/auth/login")
    public String loginProcess(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {

        Account user = accountService.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
            return "auth/login";
        }

        session.setAttribute("user", user);
        model.addAttribute("message", "Đăng nhập thành công!");

        // 👉 Nếu có securityUri (trang bị chặn trước đó) → quay lại
        String securityUri = (String) session.getAttribute("securityUri");
        if (securityUri != null) {
            session.removeAttribute("securityUri");
            return "redirect:" + securityUri;
        }

        // ✅ Nếu là admin → vào trang admin
        if (user.isAdmin()) {
            return "redirect:/admin/home/index";
        }

        // ✅ Còn lại (user thường) → vào trang chủ
        return "redirect:/home/index";
    }
    @GetMapping("/auth/logout")
    public String logout() {
        session.invalidate(); // xóa session
        return "redirect:/auth/login";
    }

}
