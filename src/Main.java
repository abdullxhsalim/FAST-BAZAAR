import java.util.*;
public class Main {
    static ArrayList<Buyer> buyers = new ArrayList<>();
    static ArrayList<Seller> sellers = new ArrayList<>();
    static ArrayList<Courier> couriers = new ArrayList<>();
    public static void buyerFileReady() {
        buyers = Buyer.readBuyersFromFile();
    }
    public static void sellerFileReady() {
        sellers = Seller.readSellersFromFile();
    }
    public static void courierFileReady() {
        couriers = Courier.readCouriersFromFile();
    }

    public static void buyerLogic() {
        
        Scanner input = new Scanner(System.in);
        System.out.println("Buyer Page");
        System.out.println("1. Register");
        System.out.println("2. Login");
        String option = input.nextLine();
        switch (option) {
            case "1":
                registerBuyer();
                buyerLogic();
                break;
            case "2":
                loginBuyer();
                buyerLogic();
                break;
            default:
                System.out.println("Invalid option");
            input.close();
        }
    }

    public static void registerBuyer() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = input.nextLine();

        System.out.println("Enter your email:");
        String email = input.nextLine();

        System.out.println("Enter your password:");
        String password = input.nextLine();

        System.out.println("Enter your phone:");
        String phone = input.nextLine();

        System.out.println("Enter your address:");
        String address = input.nextLine();

        System.out.println("Enter your city:");
        String city = input.nextLine();

        System.out.println("Enter your zip:");
        int zip = input.nextInt();
        input.nextLine();

        System.out.println("Enter your country:");
        String country = input.nextLine();
        
        Buyer newBuyer = new Buyer(name, email, password, phone, address, city, zip, country);
        buyers.add(newBuyer);
        Buyer.updateBinaryFile(buyers);

        System.out.println("Registration successful!");
        System.out.println("You can now login");
        System.out.println("====================================");
        System.out.println("Press enter to continue");
        input.nextLine();
    }

    public static void loginBuyer() {
        // Logic for logging in an existing buyer
    }

    public static void sellerLogic() {
        // Logic for seller
    }

    public static void courierLogic() {
        // Logic for courier
    }

    public static void adminLogic() {
        // Logic for admin
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        buyerFileReady();
        sellerFileReady();
        courierFileReady();
        System.out.println("Welcome to FAST===BAZAAR!");
        System.out.println("Who is using the program?");
        System.err.println("1. Buyer");
        System.err.println("2. Seller");
        System.err.println("3. Courier");
        System.err.println("4. Admin");
        String user = input.nextLine();
        switch (user) {
            case "1":
                buyerLogic();
                break;
            case "2":
                sellerLogic();
                break;
            case "3":
                courierLogic();
                break;
            case "4":
                adminLogic();
                break;
            default:
                System.out.println("Invalid input");
            input.close();
        }
    }
}
