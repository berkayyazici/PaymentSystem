package com.paymentcheckservice.service;

import com.paymentcheckservice.dto.PaymentRequest;
import com.paymentcheckservice.dto.PaymentResponse;
import com.paymentcheckservice.entity.Payments;
import com.paymentcheckservice.model.PaymentInfo;
import com.paymentcheckservice.repository.AccountsRepository;
import com.paymentcheckservice.repository.PaymentsRepository;
import com.paymentcheckservice.repository.UsersRepository;
import com.paymentcheckservice.utils.PaymentStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentCheckService {

    private final PaymentsRepository paymentsRepository;
    private final UsersRepository usersRepository;
    private final AccountsRepository accountsRepository;
    private final KafkaTemplate<String, PaymentInfo> kafkaTemplate;

    public PaymentCheckService(PaymentsRepository paymentsRepository, UsersRepository usersRepository, AccountsRepository accountsRepository, KafkaTemplate<String, PaymentInfo> kafkaTemplate) {
        this.paymentsRepository = paymentsRepository;
        this.usersRepository = usersRepository;
        this.accountsRepository = accountsRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public PaymentResponse checkPayment(PaymentRequest paymentRequest) throws Exception {

        checkAndSend(paymentRequest);

        return new PaymentResponse(UUID.fromString(paymentRequest.getUserId()), LocalDateTime.now());
    }

    public String checkAndSend(PaymentRequest paymentRequest) throws Exception {

        if(usersRepository.findById(UUID.fromString(paymentRequest.getUserId())).isEmpty()){
            throw new Exception("User not found");
        }

        if(accountsRepository.findByUserId(UUID.fromString(paymentRequest.getUserId())) == null){
            throw new Exception("Account not found");
        }

        Payments payment = new Payments();
        payment.setUserId(UUID.fromString(paymentRequest.getUserId()));
        payment.setPaymentId(UUID.randomUUID());
        payment.setCreatedDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PENDING.toString());

        paymentsRepository.save(payment);

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setUserId(paymentRequest.getUserId());
        paymentInfo.setPaymentId(payment.getPaymentId().toString());


        kafkaTemplate.send("payment-topic", paymentInfo);
        return "Payment info sent to Kafka: " + paymentInfo;
    }

    public List<PaymentResponse> getAllPayments(UUID userId)
    {
        return paymentsRepository.findByUserId(userId).stream().map(
                p -> {
                    PaymentResponse paymentResponse = new PaymentResponse();
                    paymentResponse.setUserId(p.getUserId());
                    paymentResponse.setPaymentId(p.getPaymentId());
                    paymentResponse.setCreatedDate(p.getCreatedDate());
                    return paymentResponse;
                }).toList();
    }
}
