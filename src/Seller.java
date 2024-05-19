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
        ArrayList<Seller> sellers = readSellersFromFile();
        for (int j = 0; j < sellers.size(); j++) {
            if (sellers.get(j).getEmail().equals(this.getEmail())) {
                sellers.set(j, this);
                break;
            }
        }
        updateBinaryFile(sellers);
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
        Scanner input = new Scanner(System.in);
        viewProducts();
        System.out.println("Enter the ID of the product you want to update: ");
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
                Product updatedProduct = new Product(name, description, price, quantity, category, getName(), image, status, id);
                productList.set(productList.indexOf(currentProduct), updatedProduct);
                ArrayList<Seller> sellers = readSellersFromFile();
                for (int j = 0; j < sellers.size(); j++) {
                    if (sellers.get(j).getEmail().equals(this.getEmail())) {
                        sellers.set(j, this);
                        break;
                    }
                }
                updateBinaryFile(sellers);    
                input.close();
                return;
            }
        }
        input.close();
        System.out.println("Product with ID " + id + " is not found.");
    }
    public void updateProduct(String id, Product updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (currentProduct.getId().equals(id)) {
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
        ArrayList<Seller> sellers = readSellersFromFile();
        for (int i = 0; i < sellers.size(); i++) {
            if (sellers.get(i).getEmail().equals(this.getEmail())) {
                sellers.set(i, this);
                break;
            }
        }    
        updateBinaryFile(sellers);
    }
    private void updateBinaryFile(List<Seller> sellers) {
        try {
            File directory = new File("../data");
            if (!directory.exists()){
                directory.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream("../data/sellers.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sellers);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    private ArrayList<Seller> readSellersFromFile() {
        ArrayList<Seller> sellers = null;
        try {
            FileInputStream fileIn = new FileInputStream("../data/sellers.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object readObject = in.readObject();
            if (readObject instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<Seller> tempSellers = (ArrayList<Seller>) readObject;
                sellers = tempSellers;
            } 
            else {
                System.out.println("Read object is not an ArrayList");
            }
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found at the specified path");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Seller class not found");
            c.printStackTrace();
        }
        return sellers == null ? new ArrayList<>() : sellers;
    }
}