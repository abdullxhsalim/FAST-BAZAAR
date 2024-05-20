import java.util.*;
import java.time.*;

public class Order {
    ArrayList<Product> orderedProducts;
    private Buyer buyer;
    private Seller seller;
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
}