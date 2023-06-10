package com.project.auction.repository;

import com.project.auction.model.Auction;
import com.project.auction.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    @Query("SELECT a FROM Auction a WHERE a.situation_id = :situation_id")
    public List<Auction> getAuctionBySituationId(@Param("situation_id") Integer situation_id);
}
