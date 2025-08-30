package com.paymentcheckservice.dto;

import com.paymentcheckservice.utils.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentResponse {
    private String userId;
    private String paymentId;
    private PaymentStatus paymentStatus;
}
