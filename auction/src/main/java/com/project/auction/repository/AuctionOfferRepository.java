package com.project.auction.repository;

import com.project.auction.model.AuctionOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuctionOfferRepository extends JpaRepository<AuctionOffer, Long> {
    @Query("SELECT ao FROM AuctionOffer ao WHERE ao.auction_id = :auction_id AND ao.offer_owner = :offer_owner")
    public List<AuctionOffer> getGiveOfferAuction(@Param("auction_id") Long auction_id, @Param("offer_owner") String offer_owner);

    @Query("SELECT ao FROM AuctionOffer ao WHERE ao.auction_id = :auction_id AND ao.offer_owner = :offer_owner")
    public AuctionOffer getGiveOffer(@Param("auction_id") Long auction_id, @Param("offer_owner") String offer_owner);

    @Query("SELECT ao FROM AuctionOffer ao WHERE ao.price_offer = (SELECT max(price_offer) FROM AuctionOffer ao WHERE ao.product_id = :product_id)")
    public AuctionOffer getBiggestGiveOffer(@Param("product_id") Long product_id);
}
