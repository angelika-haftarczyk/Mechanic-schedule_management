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
        UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
        if(userPrincipal != null &&
                Optional.ofNullable(modelAndView)
                        .map(ModelAndView::getModel)
                        .map(model -> !model.containsKey("username"))
                        .orElse(false)) {
            CurrentUser user = (CurrentUser) userPrincipal.getPrincipal();
            modelAndView.getModel().put("username", user.getUser().getFirstName());
            modelAndView.getModel().put("isAdmin", user.getUser().getRoles().contains("ROLE_ADMIN"));
            modelAndView.getModel().put("view", modelAndView.getViewName());
        }
    }
}
