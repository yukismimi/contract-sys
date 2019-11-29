package com.yukismimi.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@CrossOrigin
@RestController
public class UserController {

    private UserServiceImpl userService;

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
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
        initData();
    }

    private void initData(){
        for (int i = 1; i <= 100; i++) {
            User user = new User();
            Random r = new Random();
            user.setName("user-"+i);
            user.setPhone(String.format("139%08d", i));
            userService.saveUser(user);
        }
    }
}
