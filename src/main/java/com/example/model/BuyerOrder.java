package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BuyerOrder implements Serializable {
    ArrayList<Product> orderedProducts;
    private Buyer buyer;
    private ArrayList<Seller> sellers;
    private Courier courier;
    private String status;
    private int orderID;
    private LocalDateTime orderPlacementTime;
    private LocalDateTime orderDeliveryTime;
    private double totalCost;
    private String address;
    private String city;
    private int zip;
    private String country;

    public BuyerOrder(Buyer buyer, ArrayList<Product> Cart) {
        this.buyer = buyer;
        this.sellers = new ArrayList<>();
        for (Product product : Cart) {
            if (!sellers.contains(product.getSeller())) {
                sellers.add(product.getSeller());
            }
        }
        orderID = generateBuyerOrderID();
        this.status = "Placed";
        this.orderedProducts = Cart;
        this.orderPlacementTime = LocalDateTime.now();
        HashMap<Seller, ArrayList<Product>> sellerProducts = new HashMap<>();
        for (Product product : Cart) {
            Seller seller = product.getSeller();
            if (!sellerProducts.containsKey(seller)) {
                sellerProducts.put(seller, new ArrayList<Product>());
            }
            sellerProducts.get(seller).add(product);
        }
        for (Seller seller : sellers) {
            double totalCost = 0;
            for (Product product : sellerProducts.get(seller)) {
                totalCost += product.getPrice();
            }
            this.totalCost += totalCost;
        }
        for (Map.Entry<Seller, ArrayList<Product>> seller : sellerProducts.entrySet()) {
            SellerOrder sellerOrder = new SellerOrder(this.buyer, seller.getKey(), seller.getValue());
            seller.getKey().getSellerOrders().add(sellerOrder);
            int index = com.example.view.Main.sellers.indexOf(seller.getKey());
            if (index != -1) {
                com.example.view.Main.sellers.set(index, seller.getKey());
            }
            this.courier = Courier.findCourierWithLeastDeliveries();
            if (courier != null) {
                courier.getOrders().add(sellerOrder);
            }
        }
        Seller.updateBinaryFile(com.example.view.Main.sellers);
        Courier.updateBinaryFile(com.example.view.Main.couriers);
    }

    public ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public ArrayList<Seller> getSeller() {
        return sellers;
    }

    public Courier getCourier() {
        return courier;
    }

    public String getStatus() {
        return status;
    }

    public int getBuyerOrderID() {
        return orderID;
    }

    public LocalDateTime getOrderPlacementTime() {
        return orderPlacementTime;
    }

    public LocalDateTime getOrderDeliveryTime() {
        return orderDeliveryTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public void setOrderDeliveryTime(LocalDateTime orderDeliveryTime) {
        this.orderDeliveryTime = orderDeliveryTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public static int generateBuyerOrderID() {
        Random random = new Random();
        int newId = random.nextInt(10000);
        while (Main.buyerOrderIDs.contains(newId)) {
            newId = random.nextInt(10000);
        }
        Main.buyerOrderIDs.add(newId);
        return newId;
    }

    @Override
    public String toString() {
        return "BuyerOrder {\n" +
                "  buyer: " + buyer.getName() + ",\n" +
                "  sellers: " + sellers + ",\n" +
                "  courier: " + courier.getName() + ",\n" +
                "  status: " + status + ",\n" +
                "  id: " + orderID + ",\n" +
                "  orderPlacementTime: " + orderPlacementTime + ",\n" +
                "  orderDeliveryTime: " + orderDeliveryTime + ",\n" +
                "  totalCost: " + totalCost + ",\n" +
                "  address: " + address + ",\n" +
                "  city: " + city + ",\n" +
                "  zip: " + zip + ",\n" +
                "  country: " + country + "\n" +
                "}";
    }
}