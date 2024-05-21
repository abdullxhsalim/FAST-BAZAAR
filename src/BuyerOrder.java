import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuyerOrder {
    ArrayList<Product> orderedProducts;
    private Buyer buyer;
    private ArrayList<Seller> sellers;
    private Courier courier;
    private String status;
    private String id;
    private LocalDateTime orderPlacementTime;
    private LocalDateTime orderDeliveryTime;
    private double totalCost;
    private String address;
    private String city;
    private int zip;
    private String country;
    public BuyerOrder(Buyer buyer, ArrayList<Product> Cart, Courier courier, String status, String id, double totalCost, String address, String city, int zip, String country) {
        this.buyer = buyer;
        this.sellers = new ArrayList<>();
        for (Product product : Cart) {
            if (!sellers.contains(product.getSeller())) {
                sellers.add(product.getSeller());
            }
        }
        this.courier = courier;
        this.status = status;
        this.id = id;
        this.totalCost = totalCost;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.orderedProducts = Cart;
        this.orderPlacementTime = LocalDateTime.now();
        HashMap<Seller, ArrayList<Product>> sellerProducts = new HashMap<>();
        for (Product product : Cart) {
            Seller seller = product.getSeller();
            if (!sellerProducts.containsKey(seller)) {
                sellerProducts.put(seller, new ArrayList<Product>());
            }
            sellerProducts.get(seller).add(product);
        }
        for (Map.Entry<Seller, ArrayList<Product>> seller : sellerProducts.entrySet()) {
            SellerOrder sellerOrder = new SellerOrder(this.buyer, seller.getKey(), seller.getValue());
            seller.getKey().getSellerOrders().add(sellerOrder);
        }
    }
    public ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }
    public Buyer getBuyer() {
        return buyer;
    }
    public ArrayList<Seller> getSeller() {
        return sellers;
    }
    public Courier getCourier() {
        return courier;
    }
    public String getStatus() {
        return status;
    }
    public String getId() {
        return id;
    }
    public LocalDateTime getOrderPlacementTime() {
        return orderPlacementTime;
    }
    public LocalDateTime getOrderDeliveryTime() {
        return orderDeliveryTime;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public int getZip() {
        return zip;
    }
    public String getCountry() {
        return country;
    }
    public void setOrderDeliveryTime(LocalDateTime orderDeliveryTime) {
        this.orderDeliveryTime = orderDeliveryTime;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}