package com.project.auction.controller;

import com.project.auction.model.Auction;
import com.project.auction.model.AuctionOffer;
import com.project.auction.model.Product;
import com.project.auction.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //List Process
    @GetMapping("/list-product")
    public String showListProduct(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String owner = authentication.getName();
        int situation_id = 1;

        List<Product> products = productService.findProductByOwner(owner,situation_id);

        model.addAttribute("products",products);
        return "product/list_product";
    }

    @GetMapping("/list-shortAuctionProduct")
    public String showListShortAuctionProduct(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String owner = authentication.getName();
        int situation_id = 2;

        List<Product> products = productService.findProductByOwner(owner,situation_id);

        model.addAttribute("products",products);
        return "product/list_product";
    }

    @GetMapping("/list-longAuctionProduct")
    public String showListLongAuctionProduct(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String owner = authentication.getName();
        int situation_id = 3;

        List<Product> products = productService.findProductByOwner(owner,situation_id);

        model.addAttribute("products",products);
        return "product/list_product";
    }

    @GetMapping("/list-soldAuctionProduct")
    public String showListSoldAuctionProduct(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String owner = authentication.getName();
        int situation_id = 4;

        List<Product> products = productService.findProductByOwner(owner,situation_id);

        model.addAttribute("products",products);
        return "product/list_product";
    }


    //Anasayfada 'Ürün satın al' butonuna basarak açılan sayfada ki ürünleri getirir.
    //Getririlen ürünler satış zamanı dolmamış situation_id si 0 olan ürünlerdir.
    @GetMapping("/unsold-product")
    public String showUnSoldProduct(Model model)
    {
        List<Product> products = productService.getUnsoldProduct();
        List<Auction> auctions = productService.getAuctionBySituationId(0);
        List<AuctionOffer> auction_offers = productService.getGiveOffer();

        model.addAttribute("products",products);
        model.addAttribute("auctions",auctions);
        model.addAttribute("auction_offers",auction_offers);
        return "product/unsold_product";
    }

    //unsold-product view sayfasında ki teklif fiyatlarının, ajax tarafından kullanılan metodu.
    @GetMapping("/get-giveOffer")
    @ResponseBody
    public List<AuctionOffer> getGiveOffer() {
        return productService.getGiveOffer();
    }

    //Create Process
    @PostMapping("/save-product")
    public String saveProduct(@RequestParam("file") MultipartFile file,
                              @RequestParam("name") String name,
                              @RequestParam("price") double price,
                              @RequestParam("description") String description)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String owner = authentication.getName();
        int situation_id = 1;

        productService.saveProductToDB(file, owner, name, description, price, situation_id);
        return "redirect:/list-product";
    }

    //Delete Process
    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Long id)
    {
        productService.deleteProductById(id);
        return "redirect:/list-product";
    }

    //Update Process
    @PostMapping("/change-product")
    public String changeProduct(@RequestParam("id") Long id,
                                @RequestParam("file") MultipartFile file,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("price") double price)
    {
        productService.changeProduct(id, file, name, description, price);
        return "redirect:/list-product";
    }




}
