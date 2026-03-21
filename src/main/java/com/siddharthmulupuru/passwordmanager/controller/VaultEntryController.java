package com.siddharthmulupuru.passwordmanager.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<VaultEntryResponse> getAllEntries() {
        return vaultEntryService.getAllEntries(getCurrentUser());
    }

    @PostMapping
    public VaultEntryResponse createEntry(@RequestBody VaultEntryRequest request) {
        return vaultEntryService.createEntry(getCurrentUser(), request);
    }

    @PutMapping("/{id}")
    public VaultEntryResponse updateEntry(@PathVariable UUID id, @RequestBody VaultEntryRequest request) {
        return vaultEntryService.updateEntry(id, getCurrentUser(), request);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable UUID id) {
        vaultEntryService.deleteEntry(id, getCurrentUser());
    }

    private User getCurrentUser() {
        User currentUser = (User) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();

        return currentUser;
    }
}
