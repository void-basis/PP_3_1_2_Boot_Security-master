package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String printWelcome(ModelMap model) {
        model.addAttribute("usersList", userService.listUsers());
        return "users";
    }

    @GetMapping(value = "/addUser")
    public String addUser(ModelMap model) {
        model.addAttribute("addUser", new User());
        return "addUser";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("addUser") User user){
    userService.add(user);
    return "redirect:/users";
    }

    @GetMapping(value = "/userUpdate/{id}")
    public String update(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("userUpdate", userService.getUser(id));
        return "userUpdate";
    }
    @PostMapping("/userUpdate")
    public String update(@ModelAttribute("userUpdate") User user){
        userService.updateUser(user);
        return "redirect:/users";
    }
    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
