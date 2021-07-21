package pl.coderslab.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.service.ProductService;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("")
    public String index(HttpServletRequest httpServletRequest, Model model){
//        model.addAttribute("user",user.getUser());
        if(httpServletRequest.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("products", productService.findAll());
        } else {
            model.addAttribute("products", productService.findAllActive());
        }
        return "product";
    }
}
