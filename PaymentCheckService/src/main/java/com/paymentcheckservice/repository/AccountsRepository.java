package com.paymentcheckservice.repository;

import com.paymentcheckservice.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountsRepository extends JpaRepository<Accounts, UUID> {
    public Accounts findByUserId(UUID userId);
}
