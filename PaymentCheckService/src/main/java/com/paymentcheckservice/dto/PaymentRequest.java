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
    private Integer userId;
    private Integer paymentId;
    private double amount;
    private String paymentStatus;
}
