import java.util.*;
import java.io.*;

public class Courier extends User {
    public void updateProductStatus(Product product, String status) {
        product.setStatus(status);
    }
    public Courier(String name, String email, String password, String phone, String address, String city, int zip, String country) {
        super(name, email, password, phone, address, city, zip, country);
    }
    public static ArrayList<Courier> readCouriersFromFile() {
        ArrayList<Courier> couriers = null;
        try {
            FileInputStream fileIn = new FileInputStream("../data/couriers.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object readObject = in.readObject();
            if (readObject instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<Courier> tempCouriers = (ArrayList<Courier>) readObject;
                couriers = tempCouriers;
            } 
            else {
                System.out.println("Read object is not an ArrayList");
            }
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Courier file not found at the specified path");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Seller class not found");
            c.printStackTrace();
        }
        return couriers == null ? new ArrayList<>() : couriers;
    }}