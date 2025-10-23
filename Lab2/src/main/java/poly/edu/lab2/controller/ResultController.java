package poly.edu.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResultController {

    @RequestMapping("/a")
    public String m1() {
        return "a"; // view a.html
    }

    @RequestMapping("/b")
    public String m2(Model model) {
        model.addAttribute("message", "I come from b");
        return "forward:/a";   // ?1 forward sang /a, giữ lại Model
    }

    @RequestMapping("/c")
    public String m3(RedirectAttributes params) {
        params.addAttribute("message", "I come from c");
        return "redirect:/a";  // ?2 redirect sang /a, message đi kèm query param
    }

    // ?3: muốn trả thẳng chuỗi ra trình duyệt thì dùng @ResponseBody
    @RequestMapping("/d")
    @ResponseBody
    public String m4() {
        return "I come from d";
    }
}
