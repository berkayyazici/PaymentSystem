package com.paymentcheckservice.dto;

import com.paymentcheckservice.utils.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentResponse {
    private UUID userId;
    private UUID paymentId;
    private LocalDateTime createdDate;

    public PaymentResponse(UUID userId, LocalDateTime createdDate) {
        this.userId = userId;
        this.createdDate = createdDate;
    }
}
