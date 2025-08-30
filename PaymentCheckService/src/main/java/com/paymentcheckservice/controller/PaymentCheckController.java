package com.paymentcheckservice.controller;

import com.paymentcheckservice.dto.PaymentRequest;
import com.paymentcheckservice.dto.PaymentResponse;
import com.paymentcheckservice.service.PaymentCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class PaymentCheckController {

    private final PaymentCheckService paymentCheckService;

    public PaymentCheckController(PaymentCheckService paymentCheckService) {
        this.paymentCheckService = paymentCheckService;
    }

    @PostMapping("/checkPayment")
    public PaymentResponse checkPayment(@RequestBody PaymentRequest paymentRequest) throws Exception {
        return paymentCheckService.checkPayment(paymentRequest);
    }

}
