package com.paymentprocessingservice.repository;

import com.paymentprocessingservice.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentsRepository extends JpaRepository<Payments, UUID> {
    Payments findByPaymentId(UUID paymentId);
}
