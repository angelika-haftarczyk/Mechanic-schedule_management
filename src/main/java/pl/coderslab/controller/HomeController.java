package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.exceptions.RegisterFailedException;
import pl.coderslab.model.User;
import pl.coderslab.model.dto.RegisterUserDto;
import pl.coderslab.service.RoleService;
import pl.coderslab.service.UserService;

import javax.validation.Valid;

@Controller
public class HomeController { //kontroler gdzie do endpointów dostęp mają niezalogowani użytkownicy

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET) //
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@Valid RegisterUserDto dto, BindingResult result){
        User user = null;

        if(!result.hasErrors() ){
            try {
                user = userService.findByLogin(dto.getLogin());
                if(user != null) {
                    throw new RegisterFailedException("użytkownik o takim loginie już istnieje");
                }
                if(dto.getPassword() == null || dto.getConfirmPassword() == null ||
                        !dto.getPassword().equals(dto.getConfirmPassword())) {
                    throw new RegisterFailedException("podane hasła są różne");
                }
                user = userService.registerUser(dto);

            }catch (RegisterFailedException e){
                return "register";
            }

            if(user != null && user.getId() != null) {
                return "redirect:login";
            }
        }
        return "register";
    }


    @RequestMapping(value = "/404",method = RequestMethod.GET)
    public String print404(){
        return "404";
    }
    @RequestMapping(value = "/blank",method = RequestMethod.GET)
    public String blank(){
        return "blank";
    }

}
