package poly.edu.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import poly.edu.lab5.service.*;

@Controller
public class AccountController {

    @Autowired
    CookieService cookieService;

    @Autowired
    ParamService paramService;

    @Autowired
    SessionService sessionService;

    // GET: hiển thị form đăng nhập
    @GetMapping("/account/login")
    public String login1(Model model) {
        // Đọc cookie "user" nếu có → điền sẵn username
        String username = cookieService.getValue("user");
        model.addAttribute("username", username);
        return "account/login";  // tương ứng với templates/account/login.html
    }

    // POST: xử lý form đăng nhập
    @PostMapping("/account/login")
    public String login2(Model model) {

        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);

        if (un.equals("poly") && pw.equals("123")) {
            // Lưu vào session
            sessionService.set("username", un);

            // Nếu nhớ tài khoản
            if (rm) {
                // Lưu cookie 10 ngày
                cookieService.add("user", un, 24 * 10);
            } else {
                // Xóa cookie nếu không chọn remember
                cookieService.remove("user");
            }

            model.addAttribute("message", "Đăng nhập thành công!");
            return "item/index";
        } else {
            model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
        }

        return "account/login";
    }
}
