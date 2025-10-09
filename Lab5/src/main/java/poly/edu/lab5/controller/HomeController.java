package poly.edu.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import poly.edu.lab5.service.CookieService;
import poly.edu.lab5.service.ParamService;
import poly.edu.lab5.service.SessionService;

import java.io.File;
import java.util.Date;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    ParamService paramService;

    @Autowired
    CookieService cookieService;

    @Autowired
    SessionService sessionService;

    // Trang chủ hiển thị form nhập liệu
    @GetMapping
    public String index() {
        return "form";
    }

    // Nhận dữ liệu từ form
    @PostMapping("/submit")
    public String submit(Model model, @RequestParam("file") MultipartFile file) {

        // Lấy dữ liệu từ request parameter
        String name = paramService.getString("name", "Người dùng");
        int age = paramService.getInt("age", 18);
        double mark = paramService.getDouble("mark", 0.0);
        boolean gender = paramService.getBoolean("gender", true);
        Date birth = paramService.getDate("birth", "yyyy-MM-dd");

        // Lưu file upload (nếu có)
        File savedFile = paramService.save(file, "/uploads/");

        // Lưu cookie & session
        cookieService.add("username", name, 1); // lưu cookie trong 1h
        sessionService.set("user", name);

        // Gửi dữ liệu ra view
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("mark", mark);
        model.addAttribute("gender", gender ? "Nam" : "Nữ");
        model.addAttribute("birth", birth);
        model.addAttribute("file", savedFile != null ? savedFile.getName() : "Không có");

        model.addAttribute("cookie", cookieService.getValue("username"));
        model.addAttribute("session", sessionService.get("user"));

        return "result";
    }
}
