public class Courier extends User {
    public void updateProductStatus(Product product, String status) {
        product.setStatus(status);
    }
    public Courier(String name, String email, String password, String phone, String address, String city, int zip, String country) {
        super(name, email, password, phone, address, city, zip, country);
    }
}