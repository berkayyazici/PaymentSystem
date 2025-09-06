package com.paymentprocessingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PAYMENTS")
@Getter
@Setter
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", nullable = false, updatable = false)
    private UUID id;
    private UUID userId;
    private UUID paymentId;
    private LocalDateTime createdDate;
    private String status;
}
