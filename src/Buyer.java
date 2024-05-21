import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Buyer extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 8061711001070044403L;
    private ArrayList<BuyerOrder> Orders;
    private ArrayList<Product> Cart;
    public Buyer(String name, String email, String password, String phone, String address, String city, int zip, String country) {
        super(name, email, password, phone, address, city, zip, country);
        this.Orders = new ArrayList<>();
        this.Cart = new ArrayList<>();
    }
    public ArrayList<BuyerOrder> getOrders() {
        return Orders;
    }
    public void addOrder(BuyerOrder order) {
        Orders.add(order);
        ArrayList<Buyer> buyers = readBuyersFromFile();
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i).getEmail().equals(this.getEmail())) {
                buyers.set(i, this);
                break;
            }
        }
        updateBinaryFile(buyers);
    }
    public ArrayList<Product> getCart() {
        return Cart;
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
        ArrayList<Buyer> buyers = readBuyersFromFile();
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i).getEmail().equals(this.getEmail())) {
                buyers.set(i, this);
                break;
            }
        }
        updateBinaryFile(buyers);
    }
    public void viewCart() {
        for (Product x : Cart) {
            System.out.println(x);
        }
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
    public static void updateBinaryFile(List<Buyer> buyers) {
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
    public static ArrayList<Buyer> readBuyersFromFile() {
        ArrayList<Buyer> buyers = null;
        File file = new File("../data/buyers.ser");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
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
            System.out.println("Buyer file not found at the specified path");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Buyer class not found");
            c.printStackTrace();
        } 
        return buyers == null ? new ArrayList<>() : buyers;
    }
}