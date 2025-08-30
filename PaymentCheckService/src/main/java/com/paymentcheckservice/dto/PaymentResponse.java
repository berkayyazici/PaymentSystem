package com.paymentcheckservice.dto;

import com.paymentcheckservice.utils.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentResponse {
    private Integer userId;
    private Integer paymentId;
    private PaymentStatus paymentStatus;
    private LocalDate createdDate;
}
