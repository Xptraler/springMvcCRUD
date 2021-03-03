package ru.javamentor.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import ru.javamentor.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getUsers(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "usersTest";
    }
    @GetMapping(value = "/admin")
    public String getUsersAdmin(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "usersTest";
    }

    @GetMapping("/admin/new")
    public String creationForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "/admin/new";

    }

    @PostMapping()
    public String createUser(@RequestParam String name, @RequestParam String password ) {
        User user = (User) new User(name, password
        );
        userService.createUser(user);
        return "redirect:/";

    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", userService.getUser(id));
        return "/user/show";
    }

    @GetMapping("/admin/{id}/edit")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", userService.getUser(id));
        return "/admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
