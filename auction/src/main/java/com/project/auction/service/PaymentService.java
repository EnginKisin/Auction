package com.project.auction.service;

import com.project.auction.model.Card;
import com.project.auction.model.Payment;
import com.project.auction.repository.CardRepository;
import com.project.auction.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    public CardRepository cardRepository;

    @Autowired
    public PaymentRepository paymentRepository;

    public Card getCardByOwner(String card_owner){
        return  cardRepository.getCardByOwner(card_owner);
    }
}
