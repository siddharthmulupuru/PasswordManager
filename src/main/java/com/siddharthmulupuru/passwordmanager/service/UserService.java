package com.siddharthmulupuru.passwordmanager.service;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.siddharthmulupuru.passwordmanager.dto.ChangePasswordRequest;
import com.siddharthmulupuru.passwordmanager.dto.ChangePasswordResponse;
import com.siddharthmulupuru.passwordmanager.dto.LoginResponse;
import com.siddharthmulupuru.passwordmanager.dto.VaultEntryRequest;
import com.siddharthmulupuru.passwordmanager.entity.User;
import com.siddharthmulupuru.passwordmanager.entity.VaultEntry;
import com.siddharthmulupuru.passwordmanager.exception.InvalidCredentialsException;
import com.siddharthmulupuru.passwordmanager.exception.UsernameAlreadyTakenException;
import com.siddharthmulupuru.passwordmanager.repository.UserRepository;
import com.siddharthmulupuru.passwordmanager.repository.VaultEntryRepository;
import com.siddharthmulupuru.passwordmanager.security.JWTService;

@Service
public class UserService {
    private final VaultEntryService vaultEntryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VaultEntryRepository vaultEntryRepository;
    
    @Autowired
    private Argon2PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    UserService(VaultEntryService vaultEntryService) {
        this.vaultEntryService = vaultEntryService;
    }

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
            LoginResponse loginResponse = new LoginResponse(jwtService.generateToken(foundUser),
                foundUser.getSalt());
            return loginResponse;
        }

        throw new InvalidCredentialsException();
    }

    public ChangePasswordResponse changePassword(User user, ChangePasswordRequest request) {
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        user.setSalt(request.getNewSalt());
        userRepository.save(user);

        vaultEntryRepository.deleteByUser(user);

        for (VaultEntryRequest entryRequest : request.getEntries()) {
            VaultEntry entry = new VaultEntry(user, entryRequest.getEncryptedName(),
                entryRequest.getEncryptedDescription(), entryRequest.getEncryptedWebsiteUsername(),
                entryRequest.getEncryptedWebsiteEmail(), entryRequest.getEncryptedWebsitePassword(),
                entryRequest.getEncryptedWebsite());
            vaultEntryRepository.save(entry);
        }

        ChangePasswordResponse response = new ChangePasswordResponse(jwtService.generateToken(user),
            request.getNewSalt(), vaultEntryService.getAllEntries(user));

        return response;
    }
}