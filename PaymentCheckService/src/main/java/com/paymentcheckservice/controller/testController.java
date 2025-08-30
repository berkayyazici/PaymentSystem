package com.paymentcheckservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/check")
public class testController {
    @GetMapping("/hello")
    public String hello() {
        return "PaymentCheck Service is running!";
    }
}
