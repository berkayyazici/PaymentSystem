package com.paymentprocessingservice.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class PaymentInfo implements Serializable {
    private UUID paymentId;
    private UUID userId;
}
