package com.project.auction.controller;

import com.project.auction.service.AuctionOfferService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuctionOfferController {
    private AuctionOfferService auctionOfferService;

    public AuctionOfferController(AuctionOfferService auctionOfferService){this.auctionOfferService = auctionOfferService;}
    @PostMapping("/give-offer")
    public String giveOfferAuction(@RequestParam("auction_id") Long auction_id,
                                   @RequestParam("product_id") Long product_id,
                                   @RequestParam("price_offer") Long price_offer,
                                   RedirectAttributes redirectAttributes)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String offer_owner = authentication.getName();
        if(auctionOfferService.getBiggestGiveOffer(product_id) != null){
            if(price_offer <= auctionOfferService.getBiggestGiveOffer(product_id).getPrice_offer()){
                redirectAttributes.addFlashAttribute("warningGiveOffer","Teklif son verilen tekliften yüksek olmalı!");
            }else{
                auctionOfferService.giveOfferAuction(auction_id, offer_owner, product_id, price_offer);
                redirectAttributes.addFlashAttribute("successGiveOffer","Teklif Verildi.");
            }
        }else{
            if(price_offer <= auctionOfferService.getProductById(product_id).getPrice()){
                redirectAttributes.addFlashAttribute("warningGiveOffer","Teklif ürün fiyatından yüksek olmalı!");
            }else{
                auctionOfferService.giveOfferAuction(auction_id, offer_owner, product_id, price_offer);
                redirectAttributes.addFlashAttribute("successGiveOffer","Teklif Verildi.");
            }
        }

        return "redirect:/unsold-product";
    }
}
