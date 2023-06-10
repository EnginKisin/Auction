package com.project.auction.controller;

import com.project.auction.service.AuctionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuctionController {
    private AuctionService auctionService;

    public AuctionController(AuctionService auctionService)
    {
        this.auctionService = auctionService;
    }

    @PostMapping("/sell-product")
    public String sellProduct(@RequestParam("product_id") Long product_id,
                              @RequestParam("product_situation_id") int situation_id)
    {
        auctionService.putAuctionTheProduct(product_id, situation_id);
        switch (situation_id){
            case 2:
                return "redirect:/list-shortAuctionProduct";
            case 3:
                return "redirect:/list-longAuctionProduct";
            default:
                return "redirect:/list-product";
        }
    }
}
