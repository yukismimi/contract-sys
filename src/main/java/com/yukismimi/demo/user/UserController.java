package com.yukismimi.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
public class UserController {

    UserServiceImpl userService;

    @PostMapping("/user")
    public User saveUser(@RequestBody User User){
        return userService.saveUser(User);
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable long id){
        return userService.findById(id);
    }

    @GetMapping("/user")
    public Iterable<User> findAll(){
        return userService.findAll();
    }

    @DeleteMapping("/user/{id}")
    public void removeById(@PathVariable long id){
        userService.removeById(id);
    }

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
}
