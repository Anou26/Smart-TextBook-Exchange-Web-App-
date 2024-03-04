package edu.syr.textbooks.controller;

import edu.syr.textbooks.model.User;
import edu.syr.textbooks.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userServiceImpl.createUser(user);
    }

    @PostMapping("/addMultiple")
    public List<User> addMultipleUsers(@RequestBody List<User> users) {
        List<User> addedUsers = new ArrayList<>();
        for (User user : users) {
            User addedUser = userServiceImpl.createUser(user);
            addedUsers.add(addedUser);
        }
        return addedUsers;
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userServiceImpl.getUser(username);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User user) {
        return userServiceImpl.updateUser(username, user);
    }
}
