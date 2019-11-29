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

    public User saveUser(User User){
        return userRepository.save(User);
    }

    public User findById(@PathVariable long id){
        return userRepository.findById(id).orElse(null);
    }

    public Iterable<User> findByConditon(User user){
        return userRepository.findAllByNameLikeOrPhone(user.getName(), user.getPhone());
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public void removeById(@PathVariable long id){
        userRepository.deleteById(id);
    }
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
