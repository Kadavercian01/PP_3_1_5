package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void create(User user);
    List<User> getListUsers();
    void delete(long id);
    void update(long id, User user);
    User getUser(long id);
    User getUser(String lastname);
}
