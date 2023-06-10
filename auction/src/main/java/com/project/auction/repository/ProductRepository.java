package com.project.auction.repository;

import com.project.auction.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.owner = :owner AND p.situation_id = :situation_id")
    public List<Product> findProductByOwner(@Param("owner") String owner,@Param("situation_id") int situation_id);

    @Query("SELECT p FROM Product p WHERE p.id = :product_id")
    public  Product getProductById(@Param("product_id") Long product_id);
}
