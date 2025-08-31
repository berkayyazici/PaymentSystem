package com.paymentcheckservice.controller;

import com.paymentcheckservice.dto.PaymentRequest;
import com.paymentcheckservice.dto.PaymentResponse;
import com.paymentcheckservice.service.PaymentCheckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class PaymentCheckController {

    private final PaymentCheckService paymentCheckService;

    public PaymentCheckController(PaymentCheckService paymentCheckService) {
        this.paymentCheckService = paymentCheckService;
    }

    @GetMapping("/getAllPayments")
    public List<PaymentResponse> getAllPaymentsByUserId(@RequestBody String userId){
        return paymentCheckService.getAllPayments(UUID.fromString(userId));
    }

    @PostMapping("/checkPayment")
    public PaymentResponse checkPayment(@RequestBody PaymentRequest paymentRequest) throws Exception {
        return paymentCheckService.checkPayment(paymentRequest);
    }

}
