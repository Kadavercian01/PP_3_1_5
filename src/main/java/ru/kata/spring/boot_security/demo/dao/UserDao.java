package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    User findUserById(long id);

    List<User> getAllUsers();

    void updateUser(User updateUser, long id);

    void deleteUser(long id);

    User findByUsername(String findByEmail);
}
