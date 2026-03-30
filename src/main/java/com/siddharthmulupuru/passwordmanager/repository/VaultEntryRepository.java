package com.siddharthmulupuru.passwordmanager.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddharthmulupuru.passwordmanager.entity.User;
import com.siddharthmulupuru.passwordmanager.entity.VaultEntry;

public interface VaultEntryRepository extends JpaRepository<VaultEntry, UUID> {
    List<VaultEntry> findByUser(User user);
    void deleteByUser(User user);
}