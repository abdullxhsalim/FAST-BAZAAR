import java.util.*;

public class SellerOrder {
    private Seller seller;
    private ArrayList<Product> products;
    public SellerOrder(Seller seller, ArrayList<Product> products) {
        this.seller = seller;
        this.products = products;
    }
    public Seller getSeller() {
        return seller;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    
}
