package com.project.auction.service;

import com.project.auction.model.AuctionOffer;
import com.project.auction.model.Product;
import com.project.auction.repository.AuctionOfferRepository;
import com.project.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionOfferService {
    @Autowired
    private AuctionOfferRepository auctionOfferRepository;

    @Autowired
    private ProductRepository productRepository;

    public void giveOfferAuction(Long auction_id, String offer_owner, Long product_id, Long price_offer){
        if(auctionOfferRepository.getGiveOfferAuction(auction_id, offer_owner).isEmpty()){
            AuctionOffer auctionOffer = new AuctionOffer();
            auctionOffer.setAuction_id(auction_id);
            auctionOffer.setOffer_owner(offer_owner);
            auctionOffer.setProduct_id(product_id);
            auctionOffer.setPrice_offer(price_offer);
            auctionOfferRepository.save(auctionOffer);
        }else {
            AuctionOffer auctionOffer = auctionOfferRepository.getGiveOffer(auction_id, offer_owner);
            auctionOffer.setAuction_id(auction_id);
            auctionOffer.setOffer_owner(offer_owner);
            auctionOffer.setProduct_id(product_id);
            auctionOffer.setPrice_offer(price_offer);
            auctionOfferRepository.save(auctionOffer);
        }
    }

    public AuctionOffer getBiggestGiveOffer(Long product_id){
        return auctionOfferRepository.getBiggestGiveOffer(product_id);
    }

    public Product getProductById(Long product_id){
        return productRepository.getProductById(product_id);
    }

}
