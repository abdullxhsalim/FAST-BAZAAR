import java.util.*;
import java.io.*;

public class Seller extends User implements Serializable {
    private ArrayList<Product> productList;
    public Seller(String name, String email, String password, String phone, String address, String city, int zip, String country) {
        super(name, email, password, phone, address, city, zip, country);
        this.productList = new ArrayList<Product>();
    }
    public void printInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
        System.out.println("Address: " + getAddress());
        System.out.println("City: " + getCity());
        System.out.println("Zip: " + getZip());
        System.out.println("Country: " + getCountry());
    }
    public void addProduct(Product product) {
        productList.add(product);
    }
    public void removeProduct(Product product) {
        productList.remove(product);
    }
    public void updateProduct() {
        // Update product in database
    }
    public void viewOrders() {
        // View orders
    }
    public void viewProducts() {
        // View products
    }
    public void viewProfile() {
        // View profile
    }
    public void updateProfile() {
        // Update profile
    }

}
