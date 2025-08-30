package com.paymentcheckservice.service;

import com.paymentcheckservice.dto.PaymentRequest;
import com.paymentcheckservice.dto.PaymentResponse;
import com.paymentcheckservice.entity.Payments;
import com.paymentcheckservice.exceptions.NotEnoughAmountException;
import com.paymentcheckservice.repository.PaymentCheckRepository;
import com.paymentcheckservice.utils.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentCheckService {

    private final PaymentCheckRepository paymentCheckRepository;

    public PaymentCheckService(PaymentCheckRepository paymentCheckRepository) {
        this.paymentCheckRepository = paymentCheckRepository;
    }

    public PaymentResponse checkPayment(PaymentRequest paymentRequest) {

        if (paymentRequest.getAmount() <= 0 || paymentRequest.getAmount() > 1000) {
            throw new NotEnoughAmountException();
        }

        Payments payments = new Payments();
        payments.setUserId(paymentRequest.getUserId());
        payments.setAmount(paymentRequest.getAmount());

        paymentCheckRepository.save(payments);

        return new PaymentResponse(paymentRequest.getUserId(), paymentRequest.getPaymentId(), PaymentStatus.Successful);
    }

    public List<PaymentResponse> getAllPayments()
    {
        return paymentCheckRepository.findAll().stream().map(
                p -> {
                    PaymentResponse paymentResponse = new PaymentResponse();
                    paymentResponse.setUserId(p.getUserId());
                    paymentResponse.setPaymentId(p.getId().toString());
                    paymentResponse.setPaymentStatus(PaymentStatus.Successful);

                    return paymentResponse;
                }).toList();
    }
}
