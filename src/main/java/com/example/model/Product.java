package com.example.model;

import java.io.Serializable;
import java.util.Random;

public class Product implements Serializable {
    private String name;
    private String description;
    private double price;
    private Seller seller;
    private String image;
    private int id;

    public Product(String name, String description, double price, Seller seller,
            String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.seller = seller;
        this.image = image;
        this.id = generateProductId();
    }

    public static Product getProductByID(int id) {
        com.example.view.Main.sellers = Seller.readSellersFromFile();
        for (Seller seller : com.example.view.Main.sellers) {
            for (Product product : seller.getProductList()) {
                if (product.getId() == id) {
                    return product;
                }
            }
        }
        System.out.println("Product not found.");
        return null;
    }

    public static int generateProductId() {
        Random random = new Random();
        int newId = random.nextInt(10000);
        while (Main.productIDs.contains(newId)) {
            newId = random.nextInt(10000);
        }
        Main.productIDs.add(newId);
        return newId;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public Seller getSeller() {
        return seller;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImagePath(String path) {
        this.image = path;
    }

    public String getImagePath() {
        return image;
    }

    @Override
    public String toString() {
        return String.format(
                "Product Details:\n" +
                        "-----------------\n" +
                        "Name: %s\n" +
                        "Description: %s\n" +
                        "Price: %.2f\n" +
                        "Seller: %s\n" +
                        "Image: %s\n" +
                        "Product ID: %s\n",
                name, description, price, seller.getName(), image, id);
    }
}