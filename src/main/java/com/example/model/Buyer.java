package com.example.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {
    @Serial
    private static final long serialVersionUID = 1L;
    private ArrayList<BuyerOrder> Orders;
    private ArrayList<Product> Cart;

    public Buyer() {
        super();
        this.Orders = new ArrayList<>();
        this.Cart = new ArrayList<>();
    }

    public Buyer(String name, String email, String password, String phone, String address, String city, int zip,
            String country) {
        super(name, email, password, phone, address, city, zip, country);
        this.Orders = new ArrayList<>();
        this.Cart = new ArrayList<>();
    }

    public ArrayList<BuyerOrder> getOrders() {
        return Orders;
    }

    public static Buyer loginBuyer(String email, String password) {
        com.example.view.Main.buyers = readBuyersFromFile();
        for (Buyer buyer : com.example.view.Main.buyers) {
            if (buyer.getEmail().equals(email) && buyer.getPassword().equals(password)) {
                return buyer;
            }
        }
        return null;
    }

    public void addOrder(BuyerOrder order) {
        Orders.add(order);
        com.example.view.Main.buyers = readBuyersFromFile();
        for (int i = 0; i < com.example.view.Main.buyers.size(); i++) {
            if (com.example.view.Main.buyers.get(i).getEmail().equals(this.getEmail())) {
                com.example.view.Main.buyers.set(i, this);
                break;
            }
        }
        updateBinaryFile(com.example.view.Main.buyers);
    }

    public ArrayList<Product> getCart() {
        return Cart;
    }

    public void placeOrder() {
        com.example.view.Main.buyers = readBuyersFromFile();
        BuyerOrder order = new BuyerOrder(this, Cart);
        System.out.println("Your current cart is:");
        viewCart();
        System.out.println("Total cost: " + order.getTotalCost());
        System.out.println("Type 'confirm' to place order or 'cancel' to cancel order");
        String choice = Main.input.nextLine();
        if (choice.equalsIgnoreCase("cancel")) {
            System.out.println("Order cancelled");
            return;
        }
        System.out.println("Order placed successfully");
        Cart.clear();
        addOrder(order);
    }

    public void addToCart() {
        com.example.view.Main.buyers = readBuyersFromFile();
        System.out.println("Enter product ID of the product to be added to your cart: ");
        int productID = Main.input.nextInt();
        Main.input.nextLine();
        Product product = Product.getProductByID(productID);
        if (product != null)
            Cart.add(product);
        for (int i = 0; i < com.example.view.Main.buyers.size(); i++) {
            if (com.example.view.Main.buyers.get(i).getEmail().equalsIgnoreCase(this.getEmail())) {
                com.example.view.Main.buyers.set(i, this);
                break;
            }
        }
    }

    public void addToCart(Product product) {
        Cart.add(product);
        ArrayList<Buyer> buyers = readBuyersFromFile();
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i).getEmail().equals(this.getEmail())) {
                buyers.set(i, this);
                break;
            }
        }
        updateBinaryFile(buyers);
    }

    public void removeFromCart(Product product) {
        this.Cart.remove(product);
        com.example.view.Main.buyers = readBuyersFromFile();
        for (int i = 0; i < com.example.view.Main.buyers.size(); i++) {
            if (com.example.view.Main.buyers.get(i).getEmail().equals(this.getEmail())) {
                com.example.view.Main.buyers.set(i, this);
                break;
            }
        }
        updateBinaryFile(com.example.view.Main.buyers);
    }

    public void viewCart() {
        if(Cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        for (Product x : Cart) {
            System.out.println(x);
        }
    }

    public void updateProfile() {
        System.out.println("Enter new name: ");
        String name = Main.input.nextLine();
        System.out.println("Enter new email: ");
        String email = Main.input.nextLine();
        System.out.println("Enter new password: ");
        String password = Main.input.nextLine();
        System.out.println("Enter new phone: ");
        String phone = Main.input.nextLine();
        System.out.println("Enter new address: ");
        String address = Main.input.nextLine();
        System.out.println("Enter new city: ");
        String city = Main.input.nextLine();
        System.out.println("Enter new zip: ");
        int zip = Main.input.nextInt();
        Main.input.nextLine();
        System.out.println("Enter new country: ");
        String country = Main.input.nextLine();
        setName(name);
        setEmail(email);
        setPassword(password);
        setPhone(phone);
        setAddress(address);
        setCity(city);
        setZip(zip);
        setCountry(country);
        com.example.view.Main.buyers = readBuyersFromFile();
        for (int i = 0; i < com.example.view.Main.buyers.size(); i++) {
            if (com.example.view.Main.buyers.get(i).getEmail().equals(this.getEmail())) {
                com.example.view.Main.buyers.set(i, this);
                break;
            }
        }
        updateBinaryFile(com.example.view.Main.buyers);
    }

    public static void updateBinaryFile(List<Buyer> buyers) {
        try {
            File directory = new File("src\\main\\resources\\buyers.ser");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream("src\\main\\resources\\buyers.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(buyers);
            out.close();
            fileOut.close();
            System.out.println("Buyers file updated");
        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("Buyers file not updated");
        }
    }

    public static ArrayList<Buyer> readBuyersFromFile() {
        ArrayList<Buyer> buyers = new ArrayList<>();
        File file = new File("src\\main\\resources\\buyers.ser");
        try {
            if (!file.exists() || file.length() == 0) {
                file.createNewFile();
                System.err.println("Buyers file created");
                return buyers;
            }
            FileInputStream fileIn = new FileInputStream("src\\main\\resources\\buyers.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object readObject = in.readObject();
            if (readObject instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<Buyer> tempBuyers = (ArrayList<Buyer>) readObject;
                buyers = tempBuyers;
            } else {
                System.out.println("Read object is not an ArrayList");
            }
            for(Buyer buyer: buyers) {
                for(BuyerOrder order: buyer.getOrders()) {
                    Main.buyerOrderIDs.add(order.getBuyerOrderID());
                }
            }
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Buyer file not found at the specified path");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Buyer class not found");
            c.printStackTrace();
        }
        return buyers == null ? new ArrayList<>() : buyers;
    }

    public static void browseAllProducts() {
        com.example.view.Main.sellers = Seller.readSellersFromFile();
        for (Seller seller : com.example.view.Main.sellers) {
            seller.viewProducts();
        }
    }

    public static void searchProduct() {
        System.out.println("Enter the name of the product you want to search: ");
        String name = Main.input.nextLine();
        com.example.view.Main.sellers = Seller.readSellersFromFile();
        for (Seller seller : com.example.view.Main.sellers) {
            for (Product product : seller.getProductList()) {
                if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                    System.out.println(product);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "Orders=" + Orders +
                ", Cart=" + Cart +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", city='" + getCity() + '\'' +
                ", zip=" + getZip() +
                ", country='" + getCountry() + '\'' +
                '}';
    }
}