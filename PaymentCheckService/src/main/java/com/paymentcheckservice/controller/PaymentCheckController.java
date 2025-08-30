package com.paymentcheckservice.controller;

import com.paymentcheckservice.dto.PaymentRequest;
import com.paymentcheckservice.dto.PaymentResponse;
import com.paymentcheckservice.service.PaymentCheckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PaymentCheckController {

    private final PaymentCheckService paymentCheckService;

    public PaymentCheckController(PaymentCheckService paymentCheckService) {
        this.paymentCheckService = paymentCheckService;
    }

    @GetMapping("/getAllPayments")
    public List<PaymentResponse> getAllPayments(){
        return paymentCheckService.getAllPayments();
    }

    @PostMapping("/checkPayment")
    public PaymentResponse checkPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentCheckService.checkPayment(paymentRequest);
    }

}
