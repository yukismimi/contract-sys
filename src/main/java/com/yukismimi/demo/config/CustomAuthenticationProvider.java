package com.yukismimi.demo.config;

import com.yukismimi.demo.entity.User;
import com.yukismimi.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository repository;

    @Autowired
    public CustomAuthenticationProvider(@Qualifier("userServiceImpl") UserDetailsService userDetailsService) {
        super();
        setUserDetailsService(userDetailsService);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();

        User user = repository.findByName(userDetails.getUsername()).get();
        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");
            user.setErrorTimes(user.getErrorTimes() + 1);
            if(user.getErrorTimes() == 3) {
                user.setNonLocked(false);
                user.setLastLockedTime(LocalDateTime.now());
            }
            repository.save(user);
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    user.getErrorTimes() == 3 ?
                            "User account is locked" :
                            String.format("Bad credentials, You have %d more opportunities to enter your password ", 3 - user.getErrorTimes())));
        }
        if (user.getErrorTimes() != 0) {
            user.setErrorTimes(0);
            repository.save(user);
        }
    }
}
