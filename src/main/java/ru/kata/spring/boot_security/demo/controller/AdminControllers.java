package ru.kata.spring.boot_security.demo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminControllers {

    private final UserService userService;
    private final RoleService roleService;

    public AdminControllers(UserService usersService, RoleService roleService) {
        this.userService = usersService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAdmin(Principal principal, Model model) {
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("authUser", userService.getUser(principal.getName()));
        System.out.println("Principal is: " + principal);
        return "admin/index";
    }

    @GetMapping("users/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/user";
    }

    @GetMapping("/newUser")
    public String getNewUserPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/newUser";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/newUser";
        }
        userService.create(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/users/{id}")
    public String deleteEvent(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("users/{id}/edit")
    public String getUpdateEventPage(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "/admin/editUser";
    }

    @PatchMapping("users/{id}")
    public String updateEvent(@ModelAttribute("user") @Valid User user, @PathVariable("id") long id, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/editUser";
        }
        userService.update(id, user);
        return "redirect:/admin";
    }
}
