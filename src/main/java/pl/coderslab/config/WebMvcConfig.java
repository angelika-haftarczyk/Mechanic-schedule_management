package pl.coderslab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.controller.UserModelInterceptorAdapter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

     //

    @Bean
    public HandlerInterceptor getUserModelInterceptorAdapter() {
        return new UserModelInterceptorAdapter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserModelInterceptorAdapter());
    }


}
