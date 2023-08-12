package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/restAdmin")
public class RestAdminController {

    private final UserService userService;
    private final RoleService roleService;

    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getListUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = roleService.getAllRoles();
        return new ResponseEntity(roleList, HttpStatus.OK);
    }


    @GetMapping("users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/userNew")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("id") long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("/users/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody User user) {
            userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}