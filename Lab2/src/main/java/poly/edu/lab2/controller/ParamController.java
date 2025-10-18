package poly.edu.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParamController {

    // Hiển thị form ban đầu
    @RequestMapping("/param/form")
    public String form() {
        return "form"; // load templates/form.html
    }

    // Xử lý khi submit
    @RequestMapping("/param/save/{x}")
    public String save(
            @PathVariable("x") String x,     // lấy 2021 từ URL
            @RequestParam("y") String y,     // lấy 2031 từ input form
            Model model) {

        // đưa dữ liệu ra view
        model.addAttribute("x", x);
        model.addAttribute("y", y);

        return "form";
    }
}