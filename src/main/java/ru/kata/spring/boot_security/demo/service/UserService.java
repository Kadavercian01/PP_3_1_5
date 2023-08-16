package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    void create(User user);
    List<User> getListUsers();
    void delete(long id);
    void update(User user);
    User getUser(long id);
    User findByEmail(String email);
}
