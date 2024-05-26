package com.example.model;

public class Admin {
    public static void printAllBuyers() {
        com.example.view.Main.buyers = Buyer.readBuyersFromFile();
        for (Buyer buyer : com.example.view.Main.buyers) {
            System.out.println(buyer);
        }
    }
    public static void printAllSellers() {
        com.example.view.Main.sellers = Seller.readSellersFromFile();
        for (Seller seller : com.example.view.Main.sellers) {
            System.out.println(seller);
        }
    }
    public static void printAllCouriers() {
        com.example.view.Main.couriers = Courier.readCouriersFromFile();
        for (Courier courier : com.example.view.Main.couriers) {
            System.out.println(courier);
        }
    }
}
