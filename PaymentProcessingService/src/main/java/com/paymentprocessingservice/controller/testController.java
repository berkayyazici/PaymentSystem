package com.paymentprocessingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/processing")
class testController {
    @GetMapping("/hello2")
    public String hello() {
        return "PaymentProcessing Service is running!";
    }
}
