package com.konrados.simpleauthbackend.user.controller;

import com.konrados.simpleauthbackend.user.User;
import com.konrados.simpleauthbackend.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/api/register")
    public String registerUser(@Valid @RequestBody User user) {
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/api/login")
    public String login(@Valid @RequestBody User user) {
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            if (foundUser.get().getPassword().equals(user.getPassword())) {
                return "User logged successfully";
            }
            else  {
                return "Wrong username or password";
            }
        }
        return "Wrong username or password";
    }

}
