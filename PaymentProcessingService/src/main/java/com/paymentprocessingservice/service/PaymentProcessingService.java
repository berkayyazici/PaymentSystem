package com.paymentprocessingservice.service;

import com.paymentprocessingservice.model.PaymentInfo;
import com.paymentprocessingservice.repository.PaymentHistoryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessingService {

    private final PaymentHistoryRepository paymentHistoryRepository;

    public PaymentProcessingService(PaymentHistoryRepository paymentHistoryRepository) {
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    @KafkaListener(topics = "payment-topic", groupId = "payment-processing-group")
    public void processPayment(PaymentInfo paymentInfo) {
        System.out.println("Processing payment: " + paymentInfo.getPaymentId() + "\n" +  "User ID : " + paymentInfo.getUserId());
        // Burada normalde ödeme işleme logic çalışır
    }


    public void processPayment(){

    }
}
