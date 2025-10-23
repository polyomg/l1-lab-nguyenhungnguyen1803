package Lab7.controller;

import Lab7.dao.ProductDAO;
import Lab7.entity.Product;
import Lab7.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductDAO dao;

    @Autowired
    SessionService session;

    //  BÀI 1 
//    @RequestMapping("/product/search")
//    public String search(Model model,
//                         @RequestParam("min") Optional<Double> min,
//                         @RequestParam("max") Optional<Double> max) {
//
//        double minPrice = min.orElse(Double.MIN_VALUE);
//        double maxPrice = max.orElse(Double.MAX_VALUE);
//
//        List<Product> items = dao.findByPrice(minPrice, maxPrice);
//        model.addAttribute("items", items);
//
//        return "product/search"; // file search.html
//    }

    // Bài 4
    @RequestMapping("/product/search")
    public String search(Model model,
                         @RequestParam("min") Optional<Double> min,
                         @RequestParam("max") Optional<Double> max) {

        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);

        // dùng DSL method thay cho @Query
        List<Product> items = dao.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("items", items);

        return "product/search";
    }
    
    // BÀI 2 
    @RequestMapping("/product/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {

        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable);

        model.addAttribute("page", page);
        model.addAttribute("keywords", kwords);

        return "product/search-and-page"; 
    }
    

    // Bài 5
    @RequestMapping("/product/search-and-page-dsl")
    public String searchAndPageDSL(Model model,
                                   @RequestParam("keywords") Optional<String> kw,
                                   @RequestParam("p") Optional<Integer> p) {

        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAllByNameLike("%" + kwords + "%", pageable);

        model.addAttribute("page", page);
        model.addAttribute("keywords", kwords);

        return "product/search-and-page-dsl";
    
    
    }
}
