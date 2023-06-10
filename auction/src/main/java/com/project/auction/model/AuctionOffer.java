package com.project.auction.model;

import jakarta.persistence.*;

@Entity
@Table(name = "auction_offers")
public class AuctionOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long auction_id;
    @Column(nullable = false)
    private String offer_owner;
    @Column(nullable = false)
    private Long product_id;
    @Column(nullable = false)
    private Long price_offer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }

    public String getOffer_owner() {
        return offer_owner;
    }

    public void setOffer_owner(String offer_owner) {
        this.offer_owner = offer_owner;
    }
    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getPrice_offer() {
        return price_offer;
    }

    public void setPrice_offer(Long price_offer) {
        this.price_offer = price_offer;
    }
}
