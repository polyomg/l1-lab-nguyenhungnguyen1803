package poly.edu.lab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home/index")
    public String index() {
        return "demo/home/index";
    }

    @RequestMapping("/home/about")
    public String about() {
        return "demo/home/about";
    }
}