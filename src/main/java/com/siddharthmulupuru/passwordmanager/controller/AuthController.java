package com.siddharthmulupuru.passwordmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddharthmulupuru.passwordmanager.dto.ChangePasswordRequest;
import com.siddharthmulupuru.passwordmanager.dto.ChangePasswordResponse;
import com.siddharthmulupuru.passwordmanager.dto.LoginRequest;
import com.siddharthmulupuru.passwordmanager.dto.LoginResponse;
import com.siddharthmulupuru.passwordmanager.dto.RegisterRequest;
import com.siddharthmulupuru.passwordmanager.entity.User;
import com.siddharthmulupuru.passwordmanager.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
        LoginResponse loginResponse = userService.register(request.getUsername(), request.getPassword());

        return ResponseEntity.status(201).body(loginResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = userService.login(request.getUsername(), request.getPassword());

        return ResponseEntity.status(200).body(loginResponse);
    }

    @PutMapping("/password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ChangePasswordResponse response = userService.changePassword(currentUser, request);
        
        return ResponseEntity.status(200).body(response);
    }
}
