package com.siddharthmulupuru.passwordmanager.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.siddharthmulupuru.passwordmanager.entity.User;
import com.siddharthmulupuru.passwordmanager.repository.UserRepository;
import com.siddharthmulupuru.passwordmanager.security.JWTService;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Argon2PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    // Register the method that registers the user given a username and password. Returns a JSON
    // Web Token. If registration failed, throws a Runtime Exception.
    public String register(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already taken");
        }

        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);
        userRepository.save(user);

        return jwtService.generateToken(user);
    }

    // Login method that logs in the user given a username and password. Returns a JSON Web
    // Token. If login failed, throws a Runtime Exception.
    public String login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        User foundUser = user.get();

        if (passwordEncoder.matches(password, foundUser.getPasswordHash())) {
            return jwtService.generateToken(foundUser);
        }

        throw new RuntimeException("Invalid username or password");
    }
}