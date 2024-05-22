import java.util.*;
import java.io.*;

public class Courier extends User {
    ArrayList<SellerOrder> orderToDeliver = new ArrayList<>();
    int numberOfOrdersToBeDelivered = 0;

    public Courier() {
        super();
        orderToDeliver = new ArrayList<>();
    }

    public Courier(String name, String email, String password, String phone, String address, String city, int zip,
            String country) {
        super(name, email, password, phone, address, city, zip, country);
    }

    public void addOrderToDeliver(SellerOrder order) {
        orderToDeliver.add(order);
    }

    public ArrayList<SellerOrder> getOrderToDeliver() {
        return orderToDeliver;
    }

    public void updateProductStatus(Product product, String status) {
        product.setStatus(status);
    }

    public static void updateBinaryFile(List<Courier> couriers) {
        try {
            File directory = new File(
                    "C:\\Users\\Abdullah\\OneDrive - northsouth.edu\\NSU\\241\\Courses\\CSE215L\\Project\\data\\couriers.ser");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(
                    "C:\\Users\\Abdullah\\OneDrive - northsouth.edu\\NSU\\241\\Courses\\CSE215L\\Project\\data\\couriers.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(couriers);
            out.close();
            fileOut.close();
            System.out.println("Courier file updated");
        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("Courier file not updated");
        }
    }

    public static ArrayList<Courier> readCouriersFromFile() {
        ArrayList<Courier> couriers = new ArrayList<>();
        File file = new File(
                "C:\\Users\\Abdullah\\OneDrive - northsouth.edu\\NSU\\241\\Courses\\CSE215L\\Project\\data\\couriers.ser");
        try {
            if (!file.exists() || file.length() == 0) {
                file.createNewFile();
                System.out.println("Couriers file created");
                return couriers;
            }
            FileInputStream fileIn = new FileInputStream(
                    "C:\\Users\\Abdullah\\OneDrive - northsouth.edu\\NSU\\241\\Courses\\CSE215L\\Project\\data\\couriers.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object readObject = in.readObject();
            if (readObject instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<Courier> tempCouriers = (ArrayList<Courier>) readObject;
                couriers = tempCouriers;
            } else {
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
    }
    @Override
    public String toString() {
        return "Courier{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", city='" + getCity() + '\'' +
                ", zip=" + getZip() +
                ", country='" + getCountry() + '\'' +
                ", orderToDeliver=" + orderToDeliver +
                ", numberOfOrdersToBeDelivered=" + numberOfOrdersToBeDelivered +
                '}';
    }
}