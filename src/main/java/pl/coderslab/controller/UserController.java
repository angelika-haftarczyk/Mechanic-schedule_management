package pl.coderslab.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.exceptions.RegisterFailedException;
import pl.coderslab.model.User;
import pl.coderslab.model.dto.UpdateUserDto;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest httpServletRequest, Model model) {
        UpdateUserDto dto = new UpdateUserDto();
        User user = userService.findByLogin(httpServletRequest.getRemoteUser());

        dto.setEmail(user.getEmail());
        dto.setLogin(user.getLogin());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setNumberPhone(user.getNumberPhone());
        dto.setInvoice(user.isInvoice());
        if (user.getDetails() != null) {
            dto.setNip(user.getDetails().getNip());
            dto.setRegon(user.getDetails().getRegon());
            dto.setCompanyName(user.getDetails().getCompanyName());
            dto.setCompanyAddress(user.getDetails().getCompanyAddress());
        }
        model.addAttribute("user", dto);

        return "user/editUser";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String update(HttpServletRequest httpServletRequest, Model model,
                         @Valid UpdateUserDto userDto, BindingResult bindingResult) {

        if(!bindingResult.hasErrors() ){
            try {
                userDto.setLogin(httpServletRequest.getRemoteUser());
                userService.saveUser(userDto);
                return "redirect:/";
            } catch(RegisterFailedException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("user", userDto);
        return "user/editUser";
    }

}
