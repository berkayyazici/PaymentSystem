package com.paymentprocessingservice.utils;

public enum PaymentStatus {
    Pending,
    CheckingUserIsActive,
    CheckingAccountAmount,
    Approved,
    Successful,
    Failed,
    Cancelled
}
