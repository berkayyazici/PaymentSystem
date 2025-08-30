package com.paymentprocessingservice.repository;

import com.paymentprocessingservice.entity.PaymentsHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentHistoryRepository extends JpaRepository<PaymentsHistory, UUID> {
}
