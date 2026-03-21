package com.siddharthmulupuru.passwordmanager.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddharthmulupuru.passwordmanager.dto.VaultEntryRequest;
import com.siddharthmulupuru.passwordmanager.dto.VaultEntryResponse;
import com.siddharthmulupuru.passwordmanager.entity.User;
import com.siddharthmulupuru.passwordmanager.service.VaultEntryService;

@RestController
@RequestMapping("/api/vault")
public class VaultEntryController {
    @Autowired
    private VaultEntryService vaultEntryService;

    @GetMapping
    public ResponseEntity<List<VaultEntryResponse>> getAllEntries() {
        List<VaultEntryResponse> response = vaultEntryService.getAllEntries(getCurrentUser());
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<VaultEntryResponse> createEntry(@RequestBody VaultEntryRequest request) {
        VaultEntryResponse response = vaultEntryService.createEntry(getCurrentUser(), request);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaultEntryResponse> updateEntry(@PathVariable UUID id, @RequestBody VaultEntryRequest request) {
        VaultEntryResponse response = vaultEntryService.updateEntry(id, getCurrentUser(), request);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable UUID id) {
        vaultEntryService.deleteEntry(id, getCurrentUser());
        return ResponseEntity.noContent().build();
    }

    private User getCurrentUser() {
        User currentUser = (User) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();

        return currentUser;
    }
}
