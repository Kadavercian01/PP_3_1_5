package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp (UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void create(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUser(long id) {
        return userDao.findUserById(id);
    }

    @Override
    public User getUser(String lastname) {
        return userDao.findByUsername(lastname);
    }
}
