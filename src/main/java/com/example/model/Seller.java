package com.example.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
    private ArrayList<Product> productList;
    private ArrayList<SellerOrder> sellerOrders;

    public Seller() {
        super();
        this.productList = new ArrayList<Product>();
        this.sellerOrders = new ArrayList<SellerOrder>();
    }

    public Seller(String name, String email, String password, String phone, String address, String city, int zip,
            String country) {
        super(name, email, password, phone, address, city, zip, country);
        this.productList = new ArrayList<Product>();
        this.sellerOrders = new ArrayList<SellerOrder>();
    }

    public ArrayList<SellerOrder> getSellerOrders() {
        return sellerOrders;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void addProduct() {
        System.out.println("Enter name: ");
        String name = Main.input.nextLine();
        System.out.println("Enter description: ");
        String description = Main.input.nextLine();
        System.out.println("Enter price: ");
        double price = Main.input.nextDouble();
        Main.input.nextLine();
        System.out.println("Enter quantity: ");
        int quantity = Main.input.nextInt();
        Main.input.nextLine();
        System.out.println("Enter category: ");
        String category = Main.input.nextLine();
        System.out.println("Enter image: ");
        String image = Main.input.nextLine();
        System.out.println("Enter status: ");
        String status = Main.input.nextLine();
        Product product = new Product(name, description, price, quantity, category, this, image, status);
        productList.add(product);
        com.example.view.Main.sellers = readSellersFromFile();
        for (int j = 0; j < com.example.view.Main.sellers.size(); j++) {
            if (com.example.view.Main.sellers.get(j).getEmail().equals(this.getEmail())) {
                com.example.view.Main.sellers.set(j, this);
                break;
            }
        }
        updateBinaryFile(com.example.view.Main.sellers);
    }

    public void addProduct(Product product) {
        productList.add(product);
        com.example.view.Main.sellers = readSellersFromFile();
        for (int j = 0; j < com.example.view.Main.sellers.size(); j++) {
            if (com.example.view.Main.sellers.get(j).getEmail().equals(this.getEmail())) {
                com.example.view.Main.sellers.set(j, this);
                break;
            }
        }
        updateBinaryFile(com.example.view.Main.sellers);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
        ArrayList<Seller> sellers = readSellersFromFile();
        for (int j = 0; j < sellers.size(); j++) {
            if (sellers.get(j).getEmail().equals(this.getEmail())) {
                sellers.set(j, this);
                break;
            }
        }
        updateBinaryFile(sellers);
    }

    public void updateProduct() {
        viewProducts();
        System.out.println("Enter the ID of the product you want to update: ");
        int id = Main.input.nextInt();
        Main.input.nextLine();
        for (Product currentProduct : productList) {
            if (currentProduct.getId() == id) {
                System.out.println("Enter new name: ");
                String name = Main.input.nextLine();
                System.out.println("Enter new description: ");
                String description = Main.input.nextLine();
                System.out.println("Enter new price: ");
                double price = Main.input.nextDouble();
                Main.input.nextLine();
                System.out.println("Enter new quantity: ");
                int quantity = Main.input.nextInt();
                Main.input.nextLine();
                System.out.println("Enter new category: ");
                String category = Main.input.nextLine();
                System.out.println("Enter new image: ");
                String image = Main.input.nextLine();
                System.out.println("Enter new status: ");
                String status = Main.input.nextLine();
                Product updatedProduct = new Product(name, description, price, quantity, category, this, image, status);
                productList.set(productList.indexOf(currentProduct), updatedProduct);
                ArrayList<Seller> sellers = readSellersFromFile();
                for (int j = 0; j < sellers.size(); j++) {
                    if (sellers.get(j).getEmail().equals(this.getEmail())) {
                        sellers.set(j, this);
                        break;
                    }
                }
                updateBinaryFile(sellers);
                return;
            }
        }
        System.out.println("Product with ID " + id + " is not found.");
    }

    public void updateProduct(int id, Product updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (currentProduct.getId() == id) {
                productList.set(i, updatedProduct);
                ArrayList<Seller> sellers = readSellersFromFile();
                for (int j = 0; j < sellers.size(); j++) {
                    if (sellers.get(j).getEmail().equals(this.getEmail())) {
                        sellers.set(j, this);
                        break;
                    }
                }
                updateBinaryFile(sellers);
                return;
            }
        }
        System.out.println("Product with id " + id + " not found.");
    }

    public void viewSales() {
        // View sales
    }

    public void viewOrders() {
        // View orders
    }

    public void deleteProduct() {
        viewProducts();
        System.out.println("Enter the ID of the product you want to delete: ");
        int id = Main.input.nextInt();
        Main.input.nextLine();
        for (Product currentProduct : productList) {
            if (currentProduct.getId() == id) {
                productList.remove(currentProduct);
                com.example.view.Main.sellers = readSellersFromFile();
                for (int j = 0; j < com.example.view.Main.sellers.size(); j++) {
                    if (com.example.view.Main.sellers.get(j).getEmail().equals(this.getEmail())) {
                        com.example.view.Main.sellers.set(j, this);
                        break;
                    }
                }
                updateBinaryFile(com.example.view.Main.sellers);
                return;
            }
        }
        System.out.println("Product with ID " + id + " is not found.");
    }

    public void viewProducts() {
        for (Product x : productList) {
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
        com.example.view.Main.sellers = readSellersFromFile();
        for (int i = 0; i < com.example.view.Main.sellers.size(); i++) {
            if (com.example.view.Main.sellers.get(i).getEmail().equals(this.getEmail())) {
                com.example.view.Main.sellers.set(i, this);
                break;
            }
        }
        updateBinaryFile(com.example.view.Main.sellers);
    }

    public static void updateBinaryFile(List<Seller> sellers) {
        try {
            File directory = new File("src\\main\\resources\\sellers.ser");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream("src\\main\\resources\\sellers.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sellers);
            out.close();
            fileOut.close();
            System.out.println("Seller file updated");
        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("Seller file not updated");
        }
    }

    public static ArrayList<Seller> readSellersFromFile() {
        ArrayList<Seller> sellers = new ArrayList<>();
        File file = new File("src\\main\\resources\\sellers.ser");
        try {
            if (!file.exists() || file.length() == 0) {
                file.createNewFile();
                System.err.println("Sellers file created");
                return sellers;
            }
            FileInputStream fileIn = new FileInputStream("src\\main\\resources\\sellers.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object readObject = in.readObject();
            if (readObject instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<Seller> tempSellers = (ArrayList<Seller>) readObject;
                sellers = tempSellers;
            } else {
                System.out.println("Read object is not an ArrayList");
            }
            for (Seller seller : sellers) {
                for (Product product : seller.getProductList()) {
                    Main.productIDs.add(product.getId());
                }
                for(SellerOrder order: seller.getSellerOrders()) {
                    Main.sellerOrderIDs.add(order.getSellerOrderID());
                }
            }
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Seller file not found at the specified path");

        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Seller class not found");
            c.printStackTrace();
        }
        return sellers == null ? new ArrayList<>() : sellers;
    }



    @Override
    public String toString() {
        return "Seller{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", city='" + getCity() + '\'' +
                ", zip=" + getZip() +
                ", country='" + getCountry() + '\'' +
                ", productList=" + productList +
                ", sellerOrders=" + sellerOrders +
                '}';
    }
}