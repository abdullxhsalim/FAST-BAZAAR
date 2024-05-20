import java.time.LocalDateTime;

public class Product {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private Seller seller;
    private String image;
    private String status;
    private LocalDateTime dateTime;
    private String id;
    
    public Product(String name, String description, double price, int quantity, String category, Seller seller, String image, String status, String id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.seller = seller;
        this.image = image;
        this.status = status;
        this.dateTime = LocalDateTime.now();
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getDescription() {
        return description;
    }
    public String getId() {
        return id;
    }
    public String getImage() {
        return image;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public Seller getSeller() {
        return seller;
    }
    public String getStatus() {
        return status;
    }
    public String getName() {
        return name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setName(String name) {
        this.name = name;
    }    
    @Override
    public String toString() {
        return "Product {\n" +
                "  name: " + name + ",\n" +
                "  description: " + description + ",\n" +
                "  price: " + price + ",\n" +
                "  quantity: " + quantity + ",\n" +
                "  category: " + category + ",\n" +
                "  seller: " + seller + ",\n" +
                "  image: " + image + ",\n" +
                "  status: " + status + ",\n" +
                "  dateTime: " + dateTime + ",\n" +
                "  id: " + id + "\n" +
                "}";
    }
}
