public class Admin {
    public static void printAllBuyers() {
        Main.buyers = Buyer.readBuyersFromFile();
        for (Buyer buyer : Main.buyers) {
            System.out.println(buyer);
        }
    }
    public static void printAllSellers() {
        Main.sellers = Seller.readSellersFromFile();
        for (Seller seller : Main.sellers) {
            System.out.println(seller);
        }
    }
    public static void printAllCouriers() {
        Main.couriers = Courier.readCouriersFromFile();
        for (Courier courier : Main.couriers) {
            System.out.println(courier);
        }
    }
}
