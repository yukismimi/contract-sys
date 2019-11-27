package com.yukismimi.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserServiceImpl {
    
    private UserRepository userRepository;

    @PostMapping("/user")
    public User saveUser(User User){
        return userRepository.save(User);
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable long id){
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/user")
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    @DeleteMapping("/user/{id}")
    public void removeById(@PathVariable long id){
        userRepository.deleteById(id);
    }
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
