package com.siddharthmulupuru.passwordmanager.service;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.siddharthmulupuru.passwordmanager.dto.LoginResponse;
import com.siddharthmulupuru.passwordmanager.entity.User;
import com.siddharthmulupuru.passwordmanager.exception.InvalidCredentialsException;
import com.siddharthmulupuru.passwordmanager.exception.UsernameAlreadyTakenException;
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
    public LoginResponse register(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyTakenException();
        }

        String hashedPassword = passwordEncoder.encode(password);
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        String salt = Base64.getEncoder().encodeToString(saltBytes);
        
        User user = new User(username, hashedPassword, salt);
        userRepository.save(user);

        LoginResponse loginResponse = new LoginResponse(jwtService.generateToken(user), salt);
        return loginResponse;
    }

    // Login method that logs in the user given a username and password. Returns a JSON Web
    // Token. If login failed, throws a Runtime Exception.
    public LoginResponse login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        User foundUser = user.get();

        if (passwordEncoder.matches(password, foundUser.getPasswordHash())) {
            LoginResponse loginResponse = new LoginResponse(jwtService.generateToken(foundUser), foundUser.getSalt());
            return loginResponse;
        }

        throw new InvalidCredentialsException();
    }
}