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
                System.err.println("Couriers file created");
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
        Main.couriers = readCouriersFromFile();
        for (int i = 0; i < Main.couriers.size(); i++) {
            if (Main.couriers.get(i).getEmail().equals(this.getEmail())) {
                Main.couriers.set(i, this);
                break;
            }
        }
        updateBinaryFile(Main.couriers);
    }

    public void deliverOrder(SellerOrder order) {
        File file = new File(
                "C:\\Users\\Abdullah\\OneDrive - northsouth.edu\\NSU\\241\\Courses\\CSE215L\\Project\\data\\couriers.ser");
        try {
            int min;
            FileInputStream fileIn = new FileInputStream(
                    "C:\\Users\\Abdullah\\OneDrive - northsouth.edu\\NSU\\241\\Courses\\CSE215L\\Project\\data\\couriers.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Courier courier;
            min = Integer.MAX_VALUE;
            while (fileIn.available() > 0) {
                courier = (Courier) in.readObject();
                if (courier.numberOfOrdersToBeDelivered < min) {
                    min = courier.numberOfOrdersToBeDelivered;
                }
            }

            while (fileIn.available() > 0) {
                courier = (Courier) in.readObject();
                if (courier.numberOfOrdersToBeDelivered == min) {
                    courier.orderToDeliver.add(order);
                    courier.numberOfOrdersToBeDelivered++;
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Courier file not found at the specified path");
        } catch (ClassNotFoundException c) {
            System.out.println("Courier class not found");
            c.printStackTrace();

        }
    }

    public void changeOrderStatus(SellerOrder order, String status) {
        for (int i = 0; i < orderToDeliver.size(); i++) {
            if (orderToDeliver.get(i).equals(order)) {
                orderToDeliver.get(i).setStatus(status);
                break;
            }
        }
        ArrayList<Courier> couriers = readCouriersFromFile();
        for (int i = 0; i < couriers.size(); i++) {
            if (couriers.get(i).getEmail().equals(this.getEmail())) {
                couriers.set(i, this);
                break;
            }
        }
        updateBinaryFile(couriers);
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