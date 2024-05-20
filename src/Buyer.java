import java.util.*;
import java.io.*;

public class Buyer extends User implements Serializable {
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
    public void viewCart() {
        for (Product product : cart) {
            System.out.println(product);
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
        ArrayList<Buyer> buyers = readBuyersFromFile();
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i).getEmail().equals(this.getEmail())) {
                buyers.set(i, this);
                break;
            }
        }    
        updateBinaryFile(buyers);
    }


    private void updateBinaryFile(List<Buyer> buyers) {
        try {
            File directory = new File("../data");
            if (!directory.exists()){
                directory.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream("../data/buyers.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(buyers);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private ArrayList<Buyer> readBuyersFromFile() {
        ArrayList<Buyer> buyers = null;
        try {
            FileInputStream fileIn = new FileInputStream("../data/buyers.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object readObject = in.readObject();
            if (readObject instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<Buyer> tempBuyers = (ArrayList<Buyer>) readObject;
                buyers = tempBuyers;
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
            System.out.println("Buyer class not found");
            c.printStackTrace();
        } 
        return buyers == null ? new ArrayList<>() : buyers;
    }

}