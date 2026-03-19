package com.siddharthmulupuru.passwordmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddharthmulupuru.passwordmanager.dto.LoginRequest;
import com.siddharthmulupuru.passwordmanager.dto.RegisterRequest;
import com.siddharthmulupuru.passwordmanager.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return userService.register(request.getUsername(), request.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request.getUsername(), request.getPassword());
    }
}
