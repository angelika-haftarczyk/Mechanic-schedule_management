package pl.coderslab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.coderslab.model.Role;
import pl.coderslab.model.User;
import pl.coderslab.service.RoleService;
import pl.coderslab.service.UserService;

@SpringBootApplication
@EnableJpaRepositories
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(UserService userService, RoleService roleService) { //funkcja ktora uruchamia sie podczas startu aplikacji (za kazdym razem)
        return (args) -> {

            if(roleService.findByName("ROLE_ADMIN")==null) { //patrzymy czy mamy role admin i jesli nie to ja tworzymy
                Role r = new Role();
                r.setName("ROLE_ADMIN");
                roleService.save(r);
            }
            if(roleService.findByName("ROLE_USER")==null) { //analogicznie do roli wyzej
                Role r = new Role();
                r.setName("ROLE_USER");
                roleService.save(r);
            }
            if(userService.findByLogin("admin")==null){ //tworze admina

                User user = new User();
                user.setLogin("admin");
                user.setPassword("admin");
                user.setFirstName("admin");
                user.setLastName("admin");
                user.setNumberPhone("000 000 000");
                user.setEmail("admin@admin.coderslab.pl");
                userService.saveAdmin(user);
            }
        };
    }
}
