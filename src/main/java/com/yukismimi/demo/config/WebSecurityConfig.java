package com.yukismimi.demo.config;

import com.yukismimi.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/login.html").permitAll()
//                .antMatchers("/register.html").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/user/**").hasAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/user-login")
//                .failureForwardUrl()
//                .failureUrl()
//                .successHandler(customSuccessHandler)
//                .failureHandler(customFailureHandler)
//                .successForwardUrl("/index.html")
                .defaultSuccessUrl("/index.html", true)
//                .failureUrl("/login.html")
                .and()
                .logout()
//                .logoutSuccessUrl("/login.html")
//                .logoutSuccessHandler(new MyLogoutSuccessHandler("/login.html"))
                .and()
                .csrf().disable();
    }
}
