package com.project.auction.controller;

import com.project.auction.model.Card;
import com.project.auction.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.param.ChargeCreateParams;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class PaymentController {

    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }
}
