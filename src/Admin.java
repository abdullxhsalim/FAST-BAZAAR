import java.util.*;

public class Admin {
    public void printAllBuyers() {
        ArrayList<Buyer> buyers = Buyer.readBuyersFromFile();
        for (Buyer buyer : buyers) {
            System.out.println(buyer);
        }
    }
    public void printAllSellers() {
        ArrayList<Seller> sellers = Seller.readSellersFromFile();
        for (Seller seller : sellers) {
            System.out.println(seller);
        }
    }
    public void printAllCouriers() {
        ArrayList<Courier> couriers = Courier.readCouriersFromFile();
        for (Courier courier : couriers) {
            System.out.println(courier);
        }
    }
}
