package pl.coderslab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.coderslab.model.Product;
import pl.coderslab.model.Role;
import pl.coderslab.model.Schedule;
import pl.coderslab.model.User;
import pl.coderslab.model.dto.RegisterUserDto;
import pl.coderslab.service.ProductService;
import pl.coderslab.service.RoleService;
import pl.coderslab.service.ScheduleService;
import pl.coderslab.service.UserService;

import java.time.LocalDateTime;
import java.time.Month;
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
            if(userService.findByLogin("adam123")==null) { //tworze użytkownika

                RegisterUserDto user = new RegisterUserDto();
                user.setLogin("adam123");
                user.setPassword("asdasd");
                user.setConfirmPassword("asdasd");
                user.setFirstName("Adam");
                user.setLastName("Nowak");
                user.setNumberPhone("888 000 000");
                user.setEmail("adam@wp.pl");
                userService.registerUser(user);
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

    @Bean
    CommandLineRunner initSchedule(ScheduleService scheduleService, ProductService productService, UserService userService) { //funkcja ktora uruchamia sie podczas startu aplikacji (za kazdym razem)
        return (args) -> {
            List<Schedule> allSchedule = scheduleService.findAllSchedule();
            if(allSchedule.isEmpty()){
                Schedule schedule1 = new Schedule();
                List<Product> allActive = productService.findAllActive();
                User adam123 = userService.findByLogin("adam123");
                schedule1.setStartTimeWork(LocalDateTime.of(2021, Month.JULY, 17, 9, 0));
                schedule1.setService(allActive.get(0));
                schedule1.setUser(adam123);
                scheduleService.addSchedule(schedule1);
                Schedule schedule2 = new Schedule();
                schedule2.setStartTimeWork(LocalDateTime.of(2021, Month.JULY, 17, 11, 30));
                schedule2.setService(allActive.get(1));
                schedule2.setUser(adam123);
                scheduleService.addSchedule(schedule2);
                User jan123 = userService.findByLogin("jan123");
                Schedule schedule3 = new Schedule();
                schedule3.setStartTimeWork(LocalDateTime.of(2021, Month.JULY, 17, 10, 00));
                schedule3.setService(allActive.get(0));
                schedule3.setUser(jan123);
                scheduleService.addSchedule(schedule3);
            }
        };
    }
}
