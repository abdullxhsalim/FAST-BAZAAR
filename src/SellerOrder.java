import java.io.Serializable;
import java.util.ArrayList;

public class SellerOrder implements Serializable {
    private Seller seller;
    private Buyer buyer;
    private String status;
    private ArrayList<Product> products;
    public SellerOrder(Buyer buyer, Seller seller, ArrayList<Product> products) {
        this.buyer = buyer;
        this.seller = seller;
        this.products = products;
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
    public String toString() {
        return "SellerOrder{" +
                "seller=" + seller.getName() +
                ", buyer=" + buyer.getName() +
                ", status='" + status + '\'' +
                ", products=" + products +
                '}';
    }
}