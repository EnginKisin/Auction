package com.project.auction.service;

import com.project.auction.model.*;
import com.project.auction.repository.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuctionOfferRepository auctionOfferRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    public void putAuctionTheProduct(Long product_id, int product_situation_id)
    {
        Auction auction = new Auction();
        Product product = new Product();

        product = productRepository.findById(product_id).get();
        product.setSituation_id(product_situation_id);
        productRepository.save(product);

        auction.setProduct_id(product_id);
        auction.setProduct_situation_id(product_situation_id);
        auction.setSituation_id(0);

        LocalDateTime now_time = LocalDateTime.now();

        auction.setStart_date(now_time);

        switch (product_situation_id){
            case 2:
                auction.setEnd_date(now_time.plusMinutes(30));
                break;
            case 3:
                auction.setEnd_date(now_time.plusDays(1));
                break;
            default:
                auction.setEnd_date(now_time);
                break;
        }

        auctionRepository.save(auction);
    }

    /*FixedDelay özelliği , bir görevin yürütülmesinin bitiş zamanı ile
    görevin bir sonraki yürütülmesinin başlangıç zamanı arasında n milisaniyelik bir gecikme olmasını sağlar.*/

    //FixedRate özelliği , zamanlanan görevi her  n milisaniyede bir çalıştırır.

    //dakika da beş kez çalışacak şekilde
    @Scheduled(fixedRate = 1)
    public void execute() throws StripeException {
        List<Auction> auctions = auctionRepository.findAll();
        for (Auction auction : auctions){
            if(auction.getEnd_date().getDayOfMonth() == LocalDateTime.now().getDayOfMonth() &&
                auction.getEnd_date().getHour() == LocalDateTime.now().getHour() &&
                    auction.getEnd_date().getMinute() == LocalDateTime.now().getMinute() &&
                    auction.getEnd_date().getSecond() == LocalDateTime.now().getSecond()
            ){
                auction.setSituation_id(1);
                auctionRepository.save(auction);

                AuctionOffer auctionOffer = auctionOfferRepository.getBiggestGiveOffer(auction.getProduct_id());
                Card card = cardRepository.getCardByOwner(auctionOffer.getOffer_owner());

                Stripe.apiKey = "sk_test_51NCV4pEu0lkggKNUYBsvLHSO6e86ZjXdOWrOo9gpR88QRhcMj2Fv9r1If88RtBnldDt78wHTXRtoZrnBUvHAh4Qk00PG8HYnZR";
                Token token = Token.create(Map.of(
                        "card", Map.of(
                                "number", card.getNumber(),
                                "exp_month", card.getMonth(),
                                "exp_year", card.getYear(),
                                "cvc", card.getCvc()
                        )
                ));

                ChargeCreateParams params = ChargeCreateParams.builder()
                        .setAmount(auctionOffer.getPrice_offer())
                        .setCurrency("usd")
                        .setSource(token.getId()) // replace with the actual card token
                        .build();
                Charge charge = Charge.create(params);

                Payment payment = new Payment();
                payment.setPaying_owner(auctionOffer.getOffer_owner());
                payment.setProduct_id(auctionOffer.getProduct_id());
                payment.setPayment_total(auctionOffer.getPrice_offer());

                LocalDateTime payment_date = LocalDateTime.now();
                payment.setPayment_date(payment_date);

                paymentRepository.save(payment);
            }
        }
    }
}
