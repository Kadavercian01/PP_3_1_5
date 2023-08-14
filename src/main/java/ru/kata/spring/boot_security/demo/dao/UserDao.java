package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

@Repository
public interface UserDao {
    void saveUser(User user);

    User findUserById(long id);

    List<User> getAllUsers();

    void updateUser(User updateUser);

    void deleteUser(long id);

    User findByUsername(String findByEmail);
}
