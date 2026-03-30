package com.siddharthmulupuru.passwordmanager.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.siddharthmulupuru.passwordmanager.entity.User;
import com.siddharthmulupuru.passwordmanager.entity.VaultEntry;

import jakarta.transaction.Transactional;

public interface VaultEntryRepository extends JpaRepository<VaultEntry, UUID> {
    List<VaultEntry> findByUser(User user);

    @Transactional
    @Modifying
    void deleteByUser(User user);
}