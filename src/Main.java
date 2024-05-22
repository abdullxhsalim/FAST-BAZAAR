import java.io.*;
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
        System.out.println("0. Go back to main menu");
        System.out.println("-1. Exit the program");
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
            case "0":
                main(null);
                break;
            case "-1":
                System.out.println("Exiting...");
                System.exit(0);
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
        System.out.println("Here is your profile:");
        System.out.println(newBuyer);
        buyers.add(newBuyer);
        Buyer.updateBinaryFile(buyers);

        System.out.println("Registration successful!");
        System.out.println("Here is a list of all buyers:");
        for (Buyer buyer : buyers) {
            System.out.println(buyer);
        }
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
        System.out.println("Admin Page");
        System.out.println("1. View all buyers");
        System.out.println("2. View all sellers");
        System.out.println("3. View all couriers");
        System.out.println();
        System.err.println("4. View all buyers directly from ArrayList without syncing with file");
        System.err.println("5. View all sellers directly from ArrayList without syncing with file");
        System.err.println("6. View all couriers directly from ArrayList without syncing with file");
        System.out.println("0. Go back to main menu");
        System.out.println("-1. Exit the program");
        Scanner input = new Scanner(System.in);
        String option = input.nextLine();
        Admin admin = new Admin();
        switch (option) {
            case "1":
                admin.printAllBuyers();
                adminLogic();
                break;
            case "2":
                admin.printAllSellers();
                adminLogic();
                break;
            case "3":
                admin.printAllCouriers();
                adminLogic();
                break;
            case "4":
                for (Buyer buyer : buyers) {
                    System.out.println(buyer);
                }
                adminLogic();
                break;
            case "5":
                for (Seller seller : sellers) {
                    System.out.println(seller);
                }
                adminLogic();
                break;
            case "6":
                for (Courier courier : couriers) {
                    System.out.println(courier);
                }
                adminLogic();
                break;
            case "0":
                main(null);
                break;
            case "-1":
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
        }
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
