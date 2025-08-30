package com.paymentcheckservice.service;

import com.paymentcheckservice.dto.PaymentRequest;
import com.paymentcheckservice.dto.PaymentResponse;
import com.paymentcheckservice.exceptions.NotEnoughAmountException;
import com.paymentcheckservice.utils.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentCheckService {

    public PaymentResponse checkPayment(PaymentRequest paymentRequest) throws Exception {

        if (paymentRequest.getAmount() <= 0 || paymentRequest.getAmount() > 1000) {
            throw new NotEnoughAmountException();
        }

        return new PaymentResponse(paymentRequest.getUserId(), paymentRequest.getPaymentId(), PaymentStatus.Successful);
    }

}
