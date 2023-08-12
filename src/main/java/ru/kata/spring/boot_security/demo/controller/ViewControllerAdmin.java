package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class ViewControllerAdmin {

    private final UserService userService;;

    @Autowired
    public ViewControllerAdmin(UserService usersService) {
        this.userService = usersService;
    }

    @GetMapping
    public String getAdmin(Principal principal, Model model) {
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("user", userService.getUser(principal.getName()));
        return "admin/index";
    }

}
