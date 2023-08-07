package ru.kata.spring.boot_security.demo.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreatingStart {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public CreatingStart(UserService  userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void CreatingStartUsers() {

        Role adminRole = new Role("ADMIN");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        roleService.saveRole(adminRole);
        Role userRole = new Role("USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        roleService.saveRole(userRole);

        User admin = new User("Вася_Админ", "Вася_фамилия","admin@mail.ru", "admin", adminRoles);

        userService.create(admin);

        User user = new User("user", "user","user@mail.ru", "user", userRoles);

        userService.create(user);
    }
}
