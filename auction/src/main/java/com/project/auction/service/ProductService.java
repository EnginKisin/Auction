package com.project.auction.service;

import com.project.auction.model.Auction;
import com.project.auction.model.AuctionOffer;
import com.project.auction.model.Product;
import com.project.auction.repository.AuctionOfferRepository;
import com.project.auction.repository.AuctionRepository;
import com.project.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionOfferRepository auctionOfferRepository;

    public void  saveProductToDB(MultipartFile file, String owner, String name, String description, double price, int situation_id)
    {
        Product p = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("geçerli bir dosya değil");
        }
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        p.setOwner(owner);
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setSituation_id(situation_id);

        productRepo.save(p);
    }
    public List<Product> getAllProduct() { return productRepo.findAll(); }

    public List<Product> findProductByOwner(String owner, int situation_id)
    {
        return productRepo.findProductByOwner(owner,situation_id);
    }

    public List<Product> getUnsoldProduct()
    {
        List<Product> products = productRepo.findAll();
        List<Auction> auctions = auctionRepository.findAll();
        List<Product> auction_products = new ArrayList<>();

        for(Product product : products){
            for(Auction auction : auctions){
                if (product.getId() == auction.getProduct_id() &&
                        (auction.getProduct_situation_id() == 2 || auction.getProduct_situation_id() == 3) &&
                        auction.getSituation_id() == 0)
                {
                    auction_products.add(product);
                }
            }
        }
        return auction_products;
    }

    public List<Auction> getAuctionBySituationId(Integer situation_id){
         return auctionRepository.getAuctionBySituationId(situation_id);
    }
    public void deleteProductById(Long id)
    {
        productRepo.deleteById(id);
    }
    public void changeProduct(Long id, MultipartFile file, String name, String description, double price)
    {
        Product p = new Product();
        p = productRepo.findById(id).get();

        if(!file.isEmpty()) {
            try {
                p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        productRepo.save(p);
    }

    public List<AuctionOffer> getGiveOffer(){
        List<AuctionOffer> allAuctionOffers = auctionOfferRepository.findAll();
        List<Product> products = productRepo.findAll();
        List<AuctionOffer> auctionOffers = new ArrayList<>();

        for(Product product : products){
            for(AuctionOffer auctionOffer : allAuctionOffers){
                if(product.getId() == auctionOffer.getProduct_id()){
                    auctionOffers.add(auctionOfferRepository.getBiggestGiveOffer(product.getId()));
                    break;
                }
            }
        }

       return auctionOffers;
    }
}
