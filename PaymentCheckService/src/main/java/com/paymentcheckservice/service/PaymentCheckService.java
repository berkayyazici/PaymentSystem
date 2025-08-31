package com.paymentcheckservice.service;

import com.paymentcheckservice.dto.PaymentRequest;
import com.paymentcheckservice.dto.PaymentResponse;
import com.paymentcheckservice.entity.Payments;
import com.paymentcheckservice.exceptions.NotEnoughAmountException;
import com.paymentcheckservice.model.PaymentInfo;
import com.paymentcheckservice.repository.PaymentCheckRepository;
import com.paymentcheckservice.utils.PaymentStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentCheckService {

    private final PaymentCheckRepository paymentCheckRepository;
    private final KafkaTemplate<String, PaymentInfo> kafkaTemplate;

    public PaymentCheckService(PaymentCheckRepository paymentCheckRepository, KafkaTemplate<String, PaymentInfo> kafkaTemplate) {
        this.paymentCheckRepository = paymentCheckRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public String checkAndSend(PaymentRequest paymentRequest) {
        // Burada normalde validation yapılır
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setUserId(paymentRequest.getUserId());


        kafkaTemplate.send("payment-topic", paymentInfo);
        return "Payment info sent to Kafka: " + paymentInfo;
    }

    public PaymentResponse checkPayment(PaymentRequest paymentRequest) {

        checkAndSend(paymentRequest);

        return new PaymentResponse(paymentRequest.getUserId(), paymentRequest.getPaymentId(), PaymentStatus.Successful, LocalDate.now());
    }

    public List<PaymentResponse> getAllPayments(UUID userId)
    {
        return paymentCheckRepository.findByUserId(userId).stream().map(
                p -> {
                    PaymentResponse paymentResponse = new PaymentResponse();
                    paymentResponse.setUserId(p.getUserId());
                    paymentResponse.setPaymentId(p.getPaymentId());
                    paymentResponse.setCreatedDate(p.getCreatedDate());
                    return paymentResponse;
                }).toList();
    }
}
