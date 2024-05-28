package com.example.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class SellerOrder implements Serializable {
    private Seller seller;
    private Buyer buyer;
    private String status;
    private ArrayList<Product> products;
    private int orderID;
    private LocalDateTime deliveryDate;
    private LocalDateTime orderPlacementDate;

    public SellerOrder(Buyer buyer, Seller seller, ArrayList<Product> products) {
        this.buyer = buyer;
        this.seller = seller;
        this.products = products;
        this.status = "Placed";
        this.orderID = generatSellerOrderID();
        this.orderPlacementDate = LocalDateTime.now();
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getSellerOrderID() {
        return orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public static int generatSellerOrderID() {
        Random random = new Random();
        int newId = random.nextInt(10000);
        while (Main.sellerOrderIDs.contains(newId)) {
            newId = random.nextInt(10000);
        }
        Main.sellerOrderIDs.add(newId);
        return newId;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public LocalDateTime getOrderPlacementDate() {
        return orderPlacementDate;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Seller: ").append(seller.getName()).append("\n");
        sb.append("Buyer: ").append(buyer.getName()).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Products: \n");
        int total = 0;  
        for (Product product : products) {
            sb.append(" - ").append(product.getName()).append("\n");
            total += product.getPrice();
        }
        
        sb.append("Order ID: ").append(orderID).append("\n");
        sb.append("Total: ").append(total).append("\n");
        return sb.toString();
    }
}