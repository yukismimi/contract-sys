package com.yukismimi.demo.controller;

import com.yukismimi.demo.entity.User;
import com.yukismimi.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;


@CrossOrigin
@RestController
public class UserController {

    private UserServiceImpl userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/user")
    public User saveUser(@RequestBody User User){
        return userService.saveUser(User);
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable long id){
        return userService.findById(id);
    }

    @PostMapping("/user/condition")
    public Iterable<User> findByCondition(@RequestBody User user){
        return userService.findByConditon(user);
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
    public UserController(UserServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        initData();
    }

    private void initData(){
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            Random r = new Random();
            user.setName("user-"+i);
            user.setPhone(String.format("139%08d", i));
            user.setPassword(bCryptPasswordEncoder.encode("user-"+i));
            user.setRole(2);
            user.setErrorTimes(0);
            user.setNonLocked(true);
            user.setLastLockedTime(LocalDateTime.MIN);
            userService.saveUser(user);
        }
    }
}
