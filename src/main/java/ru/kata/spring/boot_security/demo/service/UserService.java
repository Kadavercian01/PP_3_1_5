package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(User user);

    User getUserById(Long id);

    void deleteUser(Long id);

    List<User> getAllUsers();

    void update(User user);

    boolean isUsernameExists(String username);
}
