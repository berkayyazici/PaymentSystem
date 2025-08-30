package com.paymentcheckservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentRequest {
    private String userId;
    private String paymentId;
    private double amount;
    private String paymentStatus;
}
