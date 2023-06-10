package com.project.auction.repository;

import com.project.auction.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT c FROM Card c WHERE c.card_owner = :card_owner")
    public Card getCardByOwner(@Param("card_owner") String card_owner);
}
