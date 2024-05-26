package com.example.controller;

import com.example.model.Buyer;
import com.example.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ViewCartSceneController {
    private Buyer buyer;
    @FXML
    private TextArea cartTextArea;
    @FXML
    private Button backButton;
    @FXML
    private Button checkoutButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button emptyCartButton;
    @FXML
    private VBox layout;

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
        displayCart();
    }

    private void displayCart() {
        StringBuilder cartDisplay = new StringBuilder();
        for (Product product : buyer.getCart()) {
            cartDisplay.append(product.toString()).append("\n");
        }
        cartTextArea.setText(cartDisplay.toString());
    }

    @FXML
    private void goBack() {
        // Implement the logic to go back to the previous scene
    }

    @FXML
    private void checkout() {
        // Implement the logic to checkout
    }

    @FXML
    private void removeProduct() {
        // Implement the logic to remove a product from the cart
    }

    @FXML
    private void emptyCart() {
        // Implement the logic to empty the cart
    }
}