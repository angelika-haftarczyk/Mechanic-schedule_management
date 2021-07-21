package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.exceptions.RegisterFailedException;
import pl.coderslab.model.Role;
import pl.coderslab.model.User;
import pl.coderslab.model.UserInvoiceDetails;
import pl.coderslab.model.dto.RegisterUserDto;
import pl.coderslab.model.dto.UpdateUserDto;
import pl.coderslab.repository.DetailsRepository;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //implementujemy kontrakt dla serwisu usera
    // główne zalety to łatwiejsza możliwość testowania serwisów
    // późniejsze możliwości rozwoju dzięki przesłanianiu przez interfejs
    // (możemy stworzyć nowy serwis który działa inaczej - np laczy sie z innym kontenerem danych - a dla
    // reszty aplikacji jest to przezroczyste)
    // dodatkowo mamy zapewniona separacje miedzy warstwami aplikacji (clean architecture)


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DetailsRepository detailsRepository;

    @Override
    public User saveUser(UpdateUserDto dto) {
        User user = userRepository.findByLogin(dto.getLogin());
        if((dto.getActualPassword() != null && !dto.getActualPassword().isEmpty()) || //dto dane z formularza, jakiekolwiek hasło zostało wpisane w formularzu
                (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) ||
                (dto.getConfirmPassword() != null && !dto.getConfirmPassword().isEmpty())) {
            if((dto.getActualPassword() == null || dto.getActualPassword().isEmpty()) || //jak brakuje jakiegokolwiek hasła
                    (dto.getNewPassword() == null || dto.getNewPassword().isEmpty()) ||
                    (dto.getConfirmPassword() == null || dto.getConfirmPassword().isEmpty())) {
                throw new RegisterFailedException("Nie wszystkie hasła są uzupełnione");
            }
            if(!dto.getNewPassword().equals(dto.getConfirmPassword())){
                throw new RegisterFailedException("Hasła do siebie nie pasują");
            }
            if(passwordEncoder.matches(dto.getActualPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            } else {

                throw new RegisterFailedException("Aktualne hasło nie pasuje");
            }
        }
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setNumberPhone(dto.getNumberPhone());
        user.setInvoice(dto.isInvoice());
        UserInvoiceDetails details = user.getDetails();
        if(details == null) {
            details = new UserInvoiceDetails();
            details.setUser(user);
            user.setDetails(details);
        }
        details.setCompanyAddress(dto.getCompanyAddress());
        details.setCompanyName(dto.getCompanyName());
        details.setNip(dto.getNip());
        details.setRegon(dto.getRegon());
        detailsRepository.save(details);
        return userRepository.save(user);
    }

    @Override
    public User saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setInvoice(false);
        Role userRole = roleRepository.findByName("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole,adminRole)));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByLogin(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    public User registerUser(RegisterUserDto dto) throws RegisterFailedException {
        if(!dto.getPassword().equals(dto.getConfirmPassword()) || dto.getPassword()==null || dto.getPassword().isEmpty()
                || dto.getConfirmPassword()==null || dto.getConfirmPassword().isEmpty()){
            throw new RegisterFailedException("Hasło niepoprawne");
        }
        User user = userRepository.findByLogin(dto.getLogin());
        if(user != null) {
            throw new RegisterFailedException("Login już istnieje");
        }
        Role userRole = roleRepository.findByName("ROLE_USER");

        user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setNumberPhone(dto.getNumberPhone());
        user.setInvoice(false);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
}
