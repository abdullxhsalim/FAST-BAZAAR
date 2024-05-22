import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class SellerOrder implements Serializable {
    private Seller seller;
    private Buyer buyer;
    private String status;
    private ArrayList<Product> products;
    private int orderID;

    public SellerOrder(Buyer buyer, Seller seller, ArrayList<Product> products) {
        this.buyer = buyer;
        this.seller = seller;
        this.products = products;
        this.status = "Placed";
        this.orderID = generatSellerOrderID();
    }

    public int getSellerOrderID() {
        return orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public static int generatSellerOrderID() {
        Random random = new Random();
        int newId = random.nextInt(10000);
        while (Main.sellerOrderIDs.contains(newId)) {
            newId = random.nextInt(10000);
        }
        Main.sellerOrderIDs.add(newId);
        return newId;
    }

    public String toString() {
        return "SellerOrder{" +
                "seller=" + seller.getName() +
                ", buyer=" + buyer.getName() +
                ", status='" + status + '\'' +
                ", products=" + products +
                '}';
    }
}