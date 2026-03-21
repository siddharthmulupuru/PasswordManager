package com.siddharthmulupuru.passwordmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddharthmulupuru.passwordmanager.dto.VaultEntryRequest;
import com.siddharthmulupuru.passwordmanager.dto.VaultEntryResponse;
import com.siddharthmulupuru.passwordmanager.entity.User;
import com.siddharthmulupuru.passwordmanager.entity.VaultEntry;
import com.siddharthmulupuru.passwordmanager.exception.UnauthorizedException;
import com.siddharthmulupuru.passwordmanager.exception.VaultEntryNotFoundException;
import com.siddharthmulupuru.passwordmanager.repository.VaultEntryRepository;

@Service
public class VaultEntryService {
    @Autowired
    VaultEntryRepository vaultEntryRepository;

    public List<VaultEntryResponse> getAllEntries(User user) {
        List<VaultEntry> vaultEntries = vaultEntryRepository.findByUser(user);
        List<VaultEntryResponse> vaultEntryResponses = new ArrayList<VaultEntryResponse>();

        for (VaultEntry ve : vaultEntries) {
            vaultEntryResponses.add(new VaultEntryResponse(ve));
        }

        return vaultEntryResponses;
    }

    public VaultEntryResponse createEntry(User user, VaultEntryRequest request) {
        VaultEntry vaultEntry = new VaultEntry(
            user, request.getEncryptedName(), request.getEncryptedDescription(),
            request.getEncryptedWebsiteUsername(), request.getEncryptedWebsitePassword(),
            request.getEncryptedWebsite()
        );

        VaultEntry saved = vaultEntryRepository.save(vaultEntry);
        
        return new VaultEntryResponse(saved);
    }

    public VaultEntryResponse updateEntry(UUID id, User user, VaultEntryRequest request) {
        Optional<VaultEntry> entryToUpdate = vaultEntryRepository.findById(id);

        if (entryToUpdate.isEmpty()) {
            throw new VaultEntryNotFoundException();
        }

        VaultEntry entry = entryToUpdate.get();

        if (!entry.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException();
        }

        entry.setEncryptedName(request.getEncryptedName());
        entry.setEncryptedDescription(request.getEncryptedDescription());
        entry.setEncryptedWebsiteUsername(request.getEncryptedWebsiteUsername());
        entry.setEncryptedWebsitePassword(request.getEncryptedWebsitePassword());
        entry.setEncryptedWebsite(request.getEncryptedWebsite());

        VaultEntry saved = vaultEntryRepository.save(entry);
        
        return new VaultEntryResponse(saved);
    }

    public void deleteEntry(UUID id, User user) {
        Optional<VaultEntry> entryToDelete = vaultEntryRepository.findById(id);

        if (entryToDelete.isEmpty()) {
            throw new VaultEntryNotFoundException();
        }

        VaultEntry entry = entryToDelete.get();

        if (!entry.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException();
        }

        vaultEntryRepository.delete(entry);
    }
}
