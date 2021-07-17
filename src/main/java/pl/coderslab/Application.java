package pl.coderslab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.coderslab.model.Product;
import pl.coderslab.model.Role;
import pl.coderslab.model.User;
import pl.coderslab.service.ProductService;
import pl.coderslab.service.RoleService;
import pl.coderslab.service.UserService;

import java.util.List;

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

    @Bean
    CommandLineRunner initProduct(ProductService productService) { //funkcja ktora uruchamia sie podczas startu aplikacji (za kazdym razem)
        return (args) -> {

            List<Product> all = productService.findAll();
            if (all.isEmpty()){
                Product wheelChange = new Product();
                wheelChange.setAvailable(true);
                wheelChange.setServiceName("Wymiana opon");
                wheelChange.setDescription("Wymiana 4 x opon");
                wheelChange.setPrice(100);
                wheelChange.setDurationInMinutes(30);
                productService.saveProduct(wheelChange);
                Product oilChange = new Product();
                oilChange.setAvailable(true);
                oilChange.setServiceName("Wymiana oleju");
                oilChange.setDescription("Wymiana oleju silnikowego");
                oilChange.setPrice(85.50);
                oilChange.setDurationInMinutes(60);
                productService.saveProduct(oilChange);
                Product filterChange = new Product();
                filterChange.setAvailable(false);
                filterChange.setServiceName("Wymiana filtrów");
                filterChange.setDescription("Wymiana wszystkich filtrów");
                filterChange.setPrice(1000);
                filterChange.setDurationInMinutes(120);
                productService.saveProduct(filterChange);
            }
        };
    }


}
