package com.khoubyari.example;

import com.khoubyari.example.dao.jpa.RoleRepository;
import com.khoubyari.example.dao.jpa.UserRepository;
import com.khoubyari.example.domain.Role;
import com.khoubyari.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {


    @Autowired
    UserRepository users;

    @Autowired
    RoleRepository roles;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role();
        role.setName("admin");
        role.setRepresentation("Administrator");
        roles.save(role);

        role = new Role();
        role.setName("manager");
        role.setRepresentation("Manager");
        roles.save(role);

        role = new Role();
        role.setName("agent");
        role.setRepresentation("Agent");
        roles.save(role);

        role = new Role();
        role.setName("customer");
        role.setRepresentation("Customer");
        roles.save(role);

        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword(this.passwordEncoder.encode("password"));
        user.setEnabled(true);
        user.setTokenExpired(false);
        user.setRoleId("admin");
        this.users.save(user);

        user = new User();
        user.setEmail("manager@gmail.com");
        user.setPassword(this.passwordEncoder.encode("password"));
        user.setEnabled(true);
        user.setTokenExpired(false);
        user.setRoleId("manager");
        this.users.save(user);

        user = new User();
        user.setEmail("agent@gmail.com");
        user.setPassword(this.passwordEncoder.encode("password"));
        user.setEnabled(true);
        user.setTokenExpired(false);
        user.setRoleId("agent");
        this.users.save(user);

        user = new User();
        user.setEmail("customer@gmail.com");
        user.setPassword(this.passwordEncoder.encode("password"));
        user.setEnabled(true);
        user.setTokenExpired(false);
        user.setRoleId("customer");
        this.users.save(user);

    }
}