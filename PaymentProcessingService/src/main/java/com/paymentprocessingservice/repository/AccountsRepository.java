package com.paymentprocessingservice.repository;

import com.paymentprocessingservice.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountsRepository extends JpaRepository<Accounts, UUID> {
}
