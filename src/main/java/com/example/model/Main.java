package com.example.model;

import com.example.exceptions.Exceptions;

import java.util.*;

public class Main {
    public static ArrayList<Buyer> buyers = new ArrayList<>();
    public static ArrayList<Seller> sellers = new ArrayList<>();
    public static ArrayList<Courier> couriers = new ArrayList<>();
    public static HashSet<Integer> productIDs = new HashSet<>();
    public static HashSet<Integer> buyerOrderIDs = new HashSet<>();
    public static HashSet<Integer> sellerOrderIDs = new HashSet<>();
    public static Scanner input = new Scanner(System.in);

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
        boolean running = true;
        while (running) {
            System.out.println("Buyer Page");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Go back");
            System.out.println("-1. Exit the program");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    registerBuyer();
                    break;
                case "2":
                    loginBuyer();
                    break;
                case "0":
                    running = false;
                    break;
                case "-1":
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void registerBuyer() {
        String name;
        while (true) {
            System.out.println("Enter your name:");
            String temp = input.nextLine();
            try {
                Exceptions.emailValidator(temp);
                name = temp;
                break;
            } catch (Exception e) {
                System.out.println("Invalid Name");
            }
        }
        String email;
        while (true) {
            System.out.println("Enter your email:");
            String temp = input.nextLine();
            try {
                Exceptions.emailValidator(temp);
                email = temp;
                break;
            } catch (Exception e) {
                System.out.println("Invalid email");
            }

        }

        System.out.println("Enter your password:");
        String password = input.nextLine();

        String phone;
        while (true) {
            System.out.println("Enter your phone number:");
            String temp = input.nextLine();
            try {
                Exceptions.emailValidator(temp);
                phone = temp;
                break;
            } catch (Exception e) {
                System.out.println("Invalid phone number");
            }

        }

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
        System.out.println("====================================");
        System.out.println("Press enter to continue");
        input.nextLine();
    }

    public static void loginBuyer() {
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Enter your email:");
            String email = input.nextLine();
            System.out.println("Enter your password:");
            String password = input.nextLine();
            for (Buyer buyer : buyers) {
                if (buyer.getEmail().equalsIgnoreCase(email) && buyer.getPassword().equals(password)) {
                    System.out.println("Login successful!");
                    System.out.println("Welcome " + buyer.getName());
                    loggedIn = true;
                    boolean running = true;
                    while (running) {
                        System.out.println("What would you like to do?");
                        System.out.println("1. Checkout");
                        System.out.println("2. View cart");
                        System.out.println("3. Browse all product listings");
                        System.out.println("4. Search for a product");
                        System.out.println("5. Add product to cart");
                        System.out.println("6. View profile");
                        System.out.println("7. Update profile");
                        System.out.println("0. Go back");
                        System.out.println("-1. Exit the program");
                        String option = input.nextLine();
                        switch (option) {
                            case "1":
                                buyer.placeOrder();
                                break;
                            case "2":
                                buyer.viewCart();
                                break;
                            case "3":
                                Buyer.browseAllProducts();
                                break;
                            case "4":
                                Buyer.searchProduct();
                                break;
                            case "5":
                                buyer.addToCart();
                                break;
                            case "6":
                                System.out.println(buyer);
                                break;
                            case "7":
                                buyer.updateProfile();
                                break;
                            case "0":
                                running = false;
                                break;
                            case "-1":
                                System.out.println("Exiting...");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid option");
                        }
                    }
                    break;
                }
            }
            if (!loggedIn) {
                System.out.println("Login failed!");
                System.out.println("Would you like to try again? (yes/no)");
                String tryAgain = input.nextLine();
                if (tryAgain.equals("no")) {
                    break;
                }
            }
        }
    }

    public static void sellerLogic() {
        boolean running = true;
        while (running) {
            System.out.println("Seller Page");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Go back");
            System.out.println("-1. Exit the program");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    registerSeller();
                    break;
                case "2":
                    loginSeller();
                    break;
                case "0":
                    running = false;
                    break;
                case "-1":
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void registerSeller() {

        String name;
        while (true) {
            System.out.println("Enter your name:");
            String temp = input.nextLine();
            try {
                Exceptions.emailValidator(temp);
                name = temp;
                break;
            } catch (Exception e) {
                System.out.println("Invalid Name");
            }
        }

        String email;
        while (true) {
            System.out.println("Enter your email:");
            String temp = input.nextLine();
            try {
                Exceptions.emailValidator(temp);
                email = temp;
                break;
            } catch (Exception e) {
                System.out.println("Invalid email");
            }
        }

        System.out.println("Enter your password:");
        String password = input.nextLine();

        String phone;
        while (true) {
            System.out.println("Enter your phone number:");
            String temp = input.nextLine();
            try {
                Exceptions.emailValidator(temp);
                phone = temp;
                break;
            } catch (Exception e) {
                System.out.println("Invalid phone number");
            }

        }

        System.out.println("Enter your address:");
        String address = input.nextLine();

        System.out.println("Enter your city:");
        String city = input.nextLine();

        System.out.println("Enter your zip:");
        int zip = input.nextInt();
        input.nextLine();

        System.out.println("Enter your country:");
        String country = input.nextLine();

        Seller newSeller = new Seller(name, email, password, phone, address, city, zip, country);
        System.out.println("Here is your profile:");
        System.out.println(newSeller);
        sellers.add(newSeller);
        Seller.updateBinaryFile(sellers);

        System.out.println("Registration successful!");
        System.out.println("====================================");
        System.out.println("Press enter to continue");
        input.nextLine();
    }

    public static void loginSeller() {
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Enter your email:");
            String email = input.nextLine();
            System.out.println("Enter your password:");
            String password = input.nextLine();
            for (Seller seller : sellers) {
                if (seller.getEmail().equalsIgnoreCase(email) && seller.getPassword().equals(password)) {
                    System.out.println("Login successful!");
                    System.out.println("Welcome " + seller.getName());
                    loggedIn = true;
                    boolean running = true;
                    while (running) {
                        System.out.println("What would you like to do?");
                        System.out.println("1. View profile");
                        System.out.println("2. Update profile");
                        System.out.println("3. Add product");
                        System.out.println("4. Update product");
                        System.out.println("5. Remove product");
                        System.out.println("6. View all products");
                        System.out.println("0. Go back");
                        System.out.println("-1. Exit the program");
                        String option = input.nextLine();
                        switch (option) {
                            case "1":
                                System.out.println(seller);
                                break;
                            case "2":
                                seller.updateProfile();
                                break;
                            case "3":
                                seller.addProduct();
                                break;
                            case "4":
                                seller.updateProduct();
                                break;
                            case "5":
                                seller.deleteProduct();
                                break;
                            case "6":
                                seller.viewProducts();
                                break;
                            case "0":
                                running = false;
                                break;
                            case "-1":
                                System.out.println("Exiting...");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid option");
                        }
                    }
                    break;
                }
            }
            if (!loggedIn) {
                System.out.println("Login failed!");
                System.out.println("Would you like to try again? (yes/no)");
                String tryAgain = input.nextLine();
                if (tryAgain.equals("no")) {
                    break;
                }
            }
        }
    }

    public static void courierLogic() {
        boolean running = true;
        while (running) {
            System.out.println("Courier Page");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Go back");
            System.out.println("-1. Exit the program");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    registerCourier();
                    break;
                case "2":
                    loginCourier();
                    break;
                case "0":
                    running = false;
                    break;
                case "-1":
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void registerCourier() {
        String name;
        while (true) {
            System.out.println("Enter your name:");
            String temp = input.nextLine();
            try {
                Exceptions.emailValidator(temp);
                name = temp;
                break;
            } catch (Exception e) {
                System.out.println("Invalid Name");
            }
        }

        String email;
        while (true) {
            System.out.println("Enter your email:");
            String temp = input.nextLine();
            try {
                Exceptions.emailValidator(temp);
                email = temp;
                break;
            } catch (Exception e) {
                System.out.println("Invalid email");
            }
        }

        System.out.println("Enter your password:");
        String password = input.nextLine();

        String phone;
        while (true) {
            System.out.println("Enter your phone number:");
            String temp = input.nextLine();
            try {
                Exceptions.emailValidator(temp);
                phone = temp;
                break;
            } catch (Exception e) {
                System.out.println("Invalid phone number");
            }

        }

        System.out.println("Enter your address:");
        String address = input.nextLine();

        System.out.println("Enter your city:");
        String city = input.nextLine();

        System.out.println("Enter your zip:");
        int zip = input.nextInt();
        input.nextLine();

        System.out.println("Enter your country:");
        String country = input.nextLine();

        Courier newCourier = new Courier(name, email, password, phone, address, city, zip, country);
        System.out.println("Here is your profile:");
        System.out.println(newCourier);
        couriers.add(newCourier);
        Courier.updateBinaryFile(couriers);

        System.out.println("Registration successful!");
        System.out.println("====================================");
        System.out.println("Press enter to continue");
        input.nextLine();
    }

    public static void loginCourier() {
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Enter your email:");
            String email = input.nextLine();
            System.out.println("Enter your password:");
            String password = input.nextLine();
            for (Courier courier : couriers) {
                if (courier.getEmail().equalsIgnoreCase(email) && courier.getPassword().equals(password)) {
                    System.out.println("Login successful!");
                    System.out.println("Welcome " + courier.getName());
                    loggedIn = true;
                    boolean running = true;
                    while (running) {
                        System.out.println("What would you like to do?");
                        System.out.println("1. Update order status");
                        System.out.println("2. View pending orders");
                        System.out.println("3. View completed orders");
                        System.out.println("4. View all orders");
                        System.out.println("5. View profile");
                        System.out.println("6. Update profile");
                        System.out.println("0. Go back");
                        System.out.println("-1. Exit the program");
                        String option = input.nextLine();
                        switch (option) {
                            case "1":
                                courier.updateOrderStatus();
                                break;
                            case "2":
                                courier.viewPendingOrders();
                                break;
                            case "3":
                                courier.viewCompletedOrders();
                                break;
                            case "4":
                                courier.viewAllOrders();
                                break;
                            case "5":
                                courier.updateProfile();
                                break;
                            case "0":
                                running = false;
                                break;
                            case "-1":
                                System.out.println("Exiting...");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid option");
                        }
                    }
                    break;
                }
            }
            if (!loggedIn) {
                System.out.println("Login failed!");
                System.out.println("Would you like to try again? (yes/no)");
                String tryAgain = input.nextLine();
                if (tryAgain.equals("no")) {
                    break;
                }
            }
        }
    }

    public static void adminLogic() {
        boolean running = true;
        while (running) {
            System.out.println("Admin Page");
            System.out.println("1. View all buyers");
            System.out.println("2. View all sellers");
            System.out.println("3. View all couriers");
            System.out.println("0. Go back");
            System.out.println("-1. Exit the program");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    Admin.printAllBuyers();
                    break;
                case "2":
                    Admin.printAllSellers();
                    break;
                case "3":
                    Admin.printAllCouriers();
                    break;
                case "0":
                    running = false;
                    break;
                case "-1":
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void main(String[] args) {
        buyerFileReady();
        sellerFileReady();
        courierFileReady();
        boolean running = true;
        while (running) {
            System.out.println("Welcome to FAST===BAZAAR!");
            System.out.println("Who is using the program?");
            System.out.println("1. Buyer");
            System.out.println("2. Seller");
            System.out.println("3. Courier");
            System.out.println("4. Admin");
            System.err.println("-1. Exit the program");
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
                case "-1":
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

}