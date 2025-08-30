package com.paymentprocessingservice.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymentInfo implements Serializable {
    private Integer paymentId;
    private Integer userId;
}
