package com.yukismimi.demo;

import com.yukismimi.demo.entity.Role;
import com.yukismimi.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableJpaAuditing
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableScheduling
public class DemoApplication implements CommandLineRunner {

    @Autowired
    RoleRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Role(1L, 1, "ROLE_ADMIN"));
        repository.save(new Role(2L, 2, "ROLE_USER"));
        repository.save(new Role(3L, 3, "ROLE_GUEST"));
    }
}
