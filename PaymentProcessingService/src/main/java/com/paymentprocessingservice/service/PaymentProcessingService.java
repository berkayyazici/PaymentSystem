package com.paymentprocessingservice.service;

import com.paymentprocessingservice.repository.PaymentsRepository;
import com.paymentprocessingservice.entity.Payments;
import com.paymentprocessingservice.entity.PaymentsHistory;
import com.paymentprocessingservice.model.PaymentInfo;
import com.paymentprocessingservice.repository.AccountsRepository;
import com.paymentprocessingservice.repository.PaymentHistoryRepository;
import com.paymentprocessingservice.repository.UsersRepository;
import com.paymentprocessingservice.utils.PaymentStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentProcessingService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final UsersRepository usersRepository;
    private final AccountsRepository accountsRepository;
    private final PaymentsRepository paymentsRepository;

    public PaymentProcessingService(PaymentHistoryRepository paymentHistoryRepository, UsersRepository usersRepository, AccountsRepository accountsRepository, PaymentsRepository paymentsRepository) {
        this.paymentHistoryRepository = paymentHistoryRepository;
        this.usersRepository = usersRepository;
        this.accountsRepository = accountsRepository;
        this.paymentsRepository = paymentsRepository;
    }

    @KafkaListener(topics = "payment-topic", groupId = "payment-processing-group")
    public void processPayment(PaymentInfo paymentInfo) {

        PaymentsHistory paymentsHistory = pendingPayment(paymentInfo);
        approvePayment(paymentsHistory);

        System.out.println("Processing payment...");
        // Burada normalde ödeme işleme logic çalışır
    }


    public PaymentsHistory pendingPayment(PaymentInfo paymentInfo){

        PaymentsHistory paymentsHistory = new PaymentsHistory();
        paymentsHistory.setPaymentId(paymentInfo.getPaymentId());
        paymentsHistory.setCreatedDate(LocalDateTime.now());
        paymentsHistory.setStatus(PaymentStatus.PENDING.toString());

        return paymentHistoryRepository.save(paymentsHistory);
    }

    public void approvePayment(PaymentsHistory paymentsHistory){

        if (!paymentHistoryRepository.findByPaymentId(paymentsHistory.getPaymentId()).isEmpty()){
            PaymentsHistory paymentsHistory2 = new PaymentsHistory();
            paymentsHistory2.setPaymentId(paymentsHistory.getPaymentId());
            paymentsHistory2.setCreatedDate(LocalDateTime.now());
            paymentsHistory2.setStatus(PaymentStatus.APPROVED.toString());

            paymentHistoryRepository.save(paymentsHistory2);

            Payments payments = paymentsRepository.findByPaymentId(paymentsHistory.getPaymentId());
            payments.setStatus(PaymentStatus.APPROVED.toString());
            paymentsRepository.save(payments);
        }
    }
}
