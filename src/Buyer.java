import java.util.*;
import java.io.*;

public class Buyer extends User implements Serializable{
    private ArrayList<Order> orders;
    private ArrayList<Product> cart;
    public Buyer(String name, String email, String password, String phone, String address, String city, int zip, String country) {
        super(name, email, password, phone, address, city, zip, country);
        this.orders = new ArrayList<>();
        this.cart = new ArrayList<>(); // initialize the cart
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }
    public void addOrder(Order order) {
        this.orders.add(order);
    }
    public ArrayList<Product> getCart() {
        return cart;
    }
    public void addToCart(Product product) {
        this.cart.add(product);
    }
    public void removeFromCart(Product product) {
        this.cart.remove(product);
    }
    public void viewProfile() {
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
        System.out.println("Address: " + getAddress());
        System.out.println("City: " + getCity());
        System.out.println("Zip: " + getZip());
        System.out.println("Country: " + getCountry());
    }
}