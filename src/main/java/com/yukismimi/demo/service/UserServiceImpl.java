package com.yukismimi.demo.service;

import com.yukismimi.demo.repository.RoleRepository;
import com.yukismimi.demo.entity.User;
import com.yukismimi.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    public User saveUser(User User) {
        return userRepository.save(User);
    }

    public User findById(@PathVariable long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Iterable<User> findByConditon(User user) {
        return userRepository.findAllByNameLikeOrPhone(user.getName(), user.getPhone());
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void removeById(@PathVariable long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByName(username);
        User user = userOptional.orElse(null);
        if(!user.isNonLocked() && Duration.between(user.getLastLockedTime(), LocalDateTime.now()).toMinutes() >= 3){
            user.setNonLocked(true);
            user.setErrorTimes(0);
            userRepository.save(user);
        }
//        List<GrantedAuthority> authorities = roleRepository.findAll()
//                .stream()
//                .map(Role::getDescription)
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
        String role = roleRepository.findById((long) user.getRole()).get().getDescription();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(
                username, user.getPassword()
                , true, true, true
                , user.isNonLocked(), authorities);
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
}
