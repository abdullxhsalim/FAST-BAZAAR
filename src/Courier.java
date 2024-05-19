public class Courier extends User {
    public void updateProductStatus(Product product, String status) {
        product.setStatus(status);
    }
    public Courier(String name, String email, String password, String phone, String address, String city, int zip, String country) {
        super(name, email, password, phone, address, city, zip, country);
    }
    public void viewProfile() {
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Role: Courier");
        System.out.println("Phone: " + getPhone());
        System.out.println("Address: " + getAddress());
        System.out.println("City: " + getCity());
        System.out.println("Zip: " + getZip());
        System.out.println("Country: " + getCountry());
    }
}