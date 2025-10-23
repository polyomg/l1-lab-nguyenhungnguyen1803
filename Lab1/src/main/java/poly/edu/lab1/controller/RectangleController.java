package poly.edu.lab1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RectangleController {

    @Autowired
    private HttpServletRequest request;

    // hiển thị form
    @GetMapping("/rectangle/form")
    public String form() {
        return "rectangle"; // trả về rectangle.html
    }

    // Xử lý tính toán
    @PostMapping("/rectangle/calc")
    public String calc(Model model) {
        try {
            double length = Double.parseDouble(request.getParameter("length"));
            double width = Double.parseDouble(request.getParameter("width"));

            double area = length * width;
            double perimeter = 2 * (length + width);

            model.addAttribute("area", area);
            model.addAttribute("perimeter", perimeter);
        } catch (Exception e) {
            model.addAttribute("area", null);
            model.addAttribute("perimeter", null);
            model.addAttribute("message", "Vui lòng nhập đúng số hợp lệ!");
        }

        return "rectangle";
    }
}