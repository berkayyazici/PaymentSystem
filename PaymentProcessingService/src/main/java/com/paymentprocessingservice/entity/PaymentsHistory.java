package com.paymentprocessingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PAYMENTS_HISTORY")
@Getter
@Setter
public class PaymentsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;
    private UUID paymentId;
    private LocalDateTime createdDate;
    private Integer status;
}
