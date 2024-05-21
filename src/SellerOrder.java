import java.util.ArrayList;

public class SellerOrder {
    private Seller seller;
    private Buyer buyer;
    private ArrayList<Product> products;
    public SellerOrder(Buyer buyer, Seller seller, ArrayList<Product> products) {
        this.buyer = buyer;
        this.seller = seller;
        this.products = products;
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
}