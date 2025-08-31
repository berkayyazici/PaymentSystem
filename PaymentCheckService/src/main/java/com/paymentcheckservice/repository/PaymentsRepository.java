package com.paymentcheckservice.repository;

import com.paymentcheckservice.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, UUID> {

    public List<Payments> findByUserId(UUID userId);

}
