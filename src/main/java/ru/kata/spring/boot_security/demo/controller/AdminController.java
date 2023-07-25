package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public final UserService userService;
    public final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/users")
    public String usersPage(ModelMap model) {
        model.addAttribute("usersList", userService.listUsers());
        return "users";
    }

    @GetMapping(value = "/addUser")
    public String addUser(ModelMap model) {
        model.addAttribute("addUser", new User());
        return "addUser";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("addUser") User user) {
        userService.add(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/userUpdate/{id}")
    public String update(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("userUpdate", userService.getUser(id));
        return "userUpdate";
    }

    @PostMapping("/userUpdate")
    public String update(@ModelAttribute("userUpdate") User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}
