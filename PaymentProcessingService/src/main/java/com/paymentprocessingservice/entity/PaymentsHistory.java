package com.paymentprocessingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "PAYMENTS_HISTORY")
@Getter
@Setter
public class PaymentsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "uuid", nullable = false, updatable = false)
    private UUID id;
    private Integer userId;
    private Integer paymentId;
    private LocalDate createdDate;
}
