package com.paymentcheckservice.repository;

import com.paymentcheckservice.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentCheckRepository extends JpaRepository<Payments, UUID> {

    public List<Payments> findByUserId(Integer userId);

}
