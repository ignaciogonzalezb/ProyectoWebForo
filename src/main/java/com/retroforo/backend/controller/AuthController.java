package com.retroforo.backend.controller;

import com.retroforo.backend.model.User;
import com.retroforo.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Password incorrecta");
        }

        return user;
    }
}