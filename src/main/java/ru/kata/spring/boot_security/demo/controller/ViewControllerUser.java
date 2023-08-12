package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class ViewControllerUser {

    private final UserService userService;

    @Autowired
    public ViewControllerUser(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUser(Principal principal, Model model) {
        model.addAttribute("user", userService.getUser(principal.getName()));
        return "user/index";
    }
}
