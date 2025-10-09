package poly.edu.lab1.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private HttpServletRequest request;

//    Formlogin
    @GetMapping("/login/form")
    public String form() {
        return "form"; // trả về form.html
    }

//    Code form login
    @PostMapping("/login/check")
    public String login(Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("poly".equals(username) && "123".equals(password)) {
            model.addAttribute("message", "Đăng nhập thành công!");
        } else {
            model.addAttribute("message", "Bạn đã sai rồi");
        }

        return "form"; // quay lại form.html và hiển thị thông báo
    }
}