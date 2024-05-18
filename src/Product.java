public class Product {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String seller;
    private String image;
    private String status;
    private String date;
    private String time;
    private String id;
    
    public Product(String name, String description, double price, int quantity, String category, String seller, String image, String status, String date, String time, String id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.seller = seller;
        this.image = image;
        this.status = status;
        this.date = date;
        this.time = time;
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public String getDate() {
        return date;
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
    public String getSeller() {
        return seller;
    }
    public String getStatus() {
        return status;
    }
    public String getTime() {
        return time;
    }
    public String getName() {
        return name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDate(String date) {
        this.date = date;
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
    public void setSeller(String seller) {
        this.seller = seller;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setName(String name) {
        this.name = name;
    }    
}
