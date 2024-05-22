import java.util.*;
import java.io.*;

public class Courier extends User {
    ArrayList<SellerOrder> orders = new ArrayList<>();
    private int numberOfOrdersToBeDelivered;

    public Courier() {
        super();
        orders = new ArrayList<>();
        numberOfOrdersToBeDelivered = 0;
    }

    public Courier(String name, String email, String password, String phone, String address, String city, int zip, String country) {
        super(name, email, password, phone, address, city, zip, country);
        numberOfOrdersToBeDelivered = 0;
        orders = new ArrayList<>();
    }

    public int getNumberOfOrdersToBeDelivered() {
        return numberOfOrdersToBeDelivered;
    }

    public void updateOrderStatus() {
        System.out.println("Here is a list of orders you have to deliver: ");
        viewPendingOrders();
        System.out.println("Enter the order ID of the order you want to update: ");
        int orderNumber = Main.input.nextInt();
        Main.input.nextLine();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getSellerOrderID() == orderNumber) {
                System.out.println("Enter the new status: ");
                String status = Main.input.nextLine();
                orders.get(i).setStatus(status);
                break;
            }
        }
        Main.couriers = readCouriersFromFile();
        for (int i = 0; i < Main.couriers.size(); i++) {
            if (Main.couriers.get(i).getEmail().equals(this.getEmail())) {
                Main.couriers.set(i, this);
                break;
            }
        }
        updateBinaryFile(Main.couriers);
    }

    public void addOrderToDeliver(SellerOrder order) {
        orders.add(order);
    }

    public ArrayList<SellerOrder> getOrders() {
        return orders;
    }

    public static void updateBinaryFile(List<Courier> couriers) {
        try {
            File directory = new File("C:\\Users\\Abdullah\\OneDrive - northsouth.edu\\NSU\\241\\Courses\\CSE215L\\Project\\data\\couriers.ser");
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
            System.out.println("Courier class not found");
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

    }

    public void updateOrderStatus(SellerOrder order, String status) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).equals(order)) {
                orders.get(i).setStatus(status);
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

    public void viewPendingOrders() {
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getStatus().equals("Pending")) {
                System.out.println(orders.get(i));
            }
        }
    }

    public void viewCompletedOrders() {
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getStatus().equals("Delivered")) {
                System.out.println(orders.get(i));
            }
        }
    }

    public void viewAllOrders() {
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
    }

    public static Courier findCourierWithLeastDeliveries() {
        Main.couriers = Courier.readCouriersFromFile();
        Courier courierWithLeastDeliveries = null;
        int minDeliveries = Integer.MAX_VALUE;
    
        for (Courier courier : Main.couriers) {
            int deliveries = courier.getNumberOfOrdersToBeDelivered();
            if (deliveries < minDeliveries) {
                minDeliveries = deliveries;
                courierWithLeastDeliveries = courier;
            }
        }
        
        return courierWithLeastDeliveries;
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
                ", orderToDeliver=" + orders +
                ", numberOfOrdersToBeDelivered=" + numberOfOrdersToBeDelivered +
                '}';
    }
}