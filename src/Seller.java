import java.util.*;
import java.io.*;

public class Seller extends User implements Serializable {
    private ArrayList<Product> productList;
    public Seller(String name, String email, String password, String phone, String address, String city, int zip, String country) {
        super(name, email, password, phone, address, city, zip, country);
        this.productList = new ArrayList<Product>();
    }
    public void addProduct(Product product) {
        productList.add(product);
        updateBinaryFile();
    }
    public void removeProduct(Product product) {
        productList.remove(product);
        updateBinaryFile();
    }
    public void updateProduct() {
        Scanner input = new Scanner(System.in);
        viewProducts();
        System.out.println("Enter the id of the product you want to update: ");
        String id = input.nextLine();
        for (Product currentProduct : productList) {
            if (currentProduct.getId().equals(id)) {
                System.out.println("Enter new name: ");
                String name = input.nextLine();
                System.out.println("Enter new description: ");
                String description = input.nextLine();
                System.out.println("Enter new price: ");
                double price = input.nextDouble();
                input.nextLine();
                System.out.println("Enter new quantity: ");
                int quantity = input.nextInt();
                input.nextLine();
                System.out.println("Enter new category: ");
                String category = input.nextLine();
                System.out.println("Enter new image: ");
                String image = input.nextLine();
                System.out.println("Enter new status: ");
                String status = input.nextLine();
                System.out.println("Enter new date: ");
                String date = input.nextLine();
                System.out.println("Enter new time: ");
                String time = input.nextLine();
                Product updatedProduct = new Product(name, description, price, quantity, category, getName(), image, status, date, time, id);
                productList.set(productList.indexOf(currentProduct), updatedProduct);
                updateBinaryFile();
                input.close();
                return;
            }
        }
        input.close();
        System.out.println("Product with id " + id + " not found.");
    }
        public void updateProduct(String id, Product updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (currentProduct.getId().equals(id)) {
                productList.set(i, updatedProduct);
                updateBinaryFile();
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
    public void viewProducts() {
        for(Product x: productList) {
            System.out.println(x);
        }
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
    public void updateProfile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter new name: ");
        String name = input.nextLine();
        System.out.println("Enter new email: ");
        String email = input.nextLine();
        System.out.println("Enter new password: ");
        String password = input.nextLine();
        System.out.println("Enter new phone: ");
        String phone = input.nextLine();
        System.out.println("Enter new address: ");
        String address = input.nextLine();
        System.out.println("Enter new city: ");
        String city = input.nextLine();
        System.out.println("Enter new zip: ");
        int zip = input.nextInt();
        input.nextLine();
        System.out.println("Enter new country: ");
        String country = input.nextLine();
        setName(name);
        setEmail(email);
        setPassword(password);
        setPhone(phone);
        setAddress(address);
        setCity(city);
        setZip(zip);
        setCountry(country);
        input.close();
        updateBinaryFile();
    }
    private void updateBinaryFile() {
        try {
            File directory = new File("../data");
            if (!directory.exists()){
                directory.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream("../data/sellers.ser");            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
