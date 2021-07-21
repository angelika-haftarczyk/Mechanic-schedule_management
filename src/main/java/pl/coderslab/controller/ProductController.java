package pl.coderslab.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.model.Product;
import pl.coderslab.service.ProductService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;


@AllArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("")
    public String index(HttpServletRequest httpServletRequest, Model model){
        if(httpServletRequest.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("products", productService.findAll());
        } else {
            model.addAttribute("products", productService.findAllActive());
        }
        return "product";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add() {
        return "admin/productAdd";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@Valid Product product, BindingResult result) {
        if(!result.hasErrors() ){
            productService.addProduct(product);
            return "redirect:/product";
        }
        return "admin/productAdd";
    }

    @RequestMapping(value = "/toggle",method = RequestMethod.POST)
    public String toggle(HttpServletRequest httpServletRequest) {
        long id = Long.parseLong(httpServletRequest.getParameter("id"));
        Product product = productService.getProductById(id);
        // sprawdzam czy parametr jest równy 'on' jeżeli null to false
        boolean available = Optional.ofNullable(httpServletRequest.getParameter("available")).map(a -> a.equals("on")).orElse(false);
        productService.setAvailable(product, available);

        return "redirect:/product";
    }

}
