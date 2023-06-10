package com.project.auction.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String paying_owner;
    @Column(nullable = false)
    private Long product_id;
    @Column(nullable = false)
    private Long payment_total;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payment_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaying_owner() {
        return paying_owner;
    }

    public void setPaying_owner(String paying_owner) {
        this.paying_owner = paying_owner;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getPayment_total() {
        return payment_total;
    }

    public void setPayment_total(Long payment_total) {
        this.payment_total = payment_total;
    }

    public LocalDateTime getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }
}
