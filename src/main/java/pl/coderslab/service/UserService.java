package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.model.User;
import pl.coderslab.model.dto.RegisterUserDto;
import pl.coderslab.model.dto.UpdateUserDto;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();
    User findByLogin(String login);
    User saveAdmin(User user);
    User saveUser(UpdateUserDto user);
    User registerUser(RegisterUserDto dto);
}