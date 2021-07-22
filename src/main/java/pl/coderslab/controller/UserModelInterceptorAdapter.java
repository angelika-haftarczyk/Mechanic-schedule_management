package pl.coderslab.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.security.CurrentUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class UserModelInterceptorAdapter implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //pobieram obiket zalogowania
        UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
        //pobieram nazwę view o ile istnieje(jak nie istnieje pobieram słowo redirect)
        String viewName = Optional.ofNullable(modelAndView).map(ModelAndView::getViewName).filter(name -> !name.isEmpty()).orElse("redirect:");
        if(viewName.startsWith("redirect:")) {
            return;
        }
        if(userPrincipal != null &&
                Optional.ofNullable(modelAndView)
                        .map(ModelAndView::getModel) // z modelAndView pobieram model
                        .map(model -> !model.containsKey("username")) // model nie zawiera username
                        .orElse(false)) {
            CurrentUser user = (CurrentUser) userPrincipal.getPrincipal(); //obiekt rozszerzajacy domyslny obiekt User
            modelAndView.getModel().put("username", user.getUser().getFirstName());
            modelAndView.getModel().put("isAdmin", user.getUser().getRoles().stream().anyMatch(role -> role.getName().contains("ROLE_ADMIN")));
            modelAndView.getModel().put("view", modelAndView.getViewName());
        }
    }
}
