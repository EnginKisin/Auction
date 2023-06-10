package com.project.auction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/payment-log")
    public String showPaymentLog() {
        return "admin/payment_log";
    }
}
