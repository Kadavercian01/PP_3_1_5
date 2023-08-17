package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class CreatingStart {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public CreatingStart(UserService userService, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @PostConstruct
    public void start() {
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);
        User admin = new User();
        admin.setUsername("admin");
        admin.setLastName("AdminLastName");
        admin.setEmail("admin@mail.ru");
        admin.setPassword("admin");
        admin.setAge(30);
        admin.setRoles(Collections.singletonList(adminRole));
        userService.save(admin);

        Role roleUser = new Role("ROLE_USER");
        roleRepository.save(roleUser);

        User user = new User();
        user.setUsername("user");
        user.setLastName("userLastName");
        user.setEmail("user@mail.ru");
        user.setPassword("user");
        user.setAge(40);
        user.setRoles(Collections.singletonList(roleUser));
        userService.save(user);
    }
}