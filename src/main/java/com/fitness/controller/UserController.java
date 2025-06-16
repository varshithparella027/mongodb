package com.fitness.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.model.User;
import com.fitness.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String email = user.getEmail().toLowerCase().trim();
        user.setEmail(email);
        if (userRepo.findByEmail(email).isPresent()) {
            return "Email already registered";
        }
        userRepo.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String email = user.getEmail().toLowerCase().trim();
        String password = user.getPassword().trim();

        Optional<User> existingUser = userRepo.findByEmail(email);
        if (existingUser.isPresent()) {
            String storedPassword = existingUser.get().getPassword().trim();
            if (storedPassword.equals(password)) {
                return "Login successful";
            } else {
                return "Wrong password";
            }
        } else {
            return "Email not found";
        }
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userRepo.findByEmail(email.toLowerCase().trim());
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
