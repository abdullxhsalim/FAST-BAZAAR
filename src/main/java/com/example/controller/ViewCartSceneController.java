package com.example.controller;

import com.example.model.Buyer;
import com.example.model.BuyerOrder;
import com.example.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.example.view.Main;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ViewCartSceneController {
    private Buyer buyer;
    @FXML
    private ListView<Product> cartListView;
    @FXML
    private Button goBackButton;
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
        cartListView.getItems().setAll(buyer.getCart());
        cartListView.setCellFactory(param -> new ListCell<>() {
            private final ImageView imageView = new ImageView();
            private final Text text = new Text();
            private final HBox hbox = new HBox(imageView, text);

            {
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(new Image(item.getImagePath()));
                    text.setText(item.getName() + "\n" +
                            "Price: " + item.getPrice() + "\n" +
                            "Description: " + item.getDescription() + "\n" +
                            "Sold by: " + item.getSeller().getName());
                    setGraphic(hbox);
                }
            }
        });
    }

    @FXML
    private void goBack() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInBuyerScene.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoggedInBuyerSceneController controller = loader.getController();
        controller.setBuyer(this.buyer);
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void checkout() {
        if (Main.couriers.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "There are no couriers available. This order cannot be placed.");
            return;
        }
    
        if (buyer.getCart().isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Your cart is empty.");
            return;
        }
    
        BuyerOrder order = new BuyerOrder(buyer, buyer.getCart());
        showAlert(Alert.AlertType.INFORMATION, "Your current total cost is: " + order.getTotalCost() + " Taka");
    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Place Order");
        alert.setContentText("Do you want to place the order?");
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            showAlert(Alert.AlertType.INFORMATION, "Order placed successfully");
            buyer.emptyCart();
            displayCart();
            buyer.addOrder(order);
        } 
        else {
            showAlert(Alert.AlertType.INFORMATION, "Order cancelled");
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void removeProduct() {
        Product selectedProduct = cartListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            buyer.removeFromCart(selectedProduct);
            displayCart();
        }
    }

    @FXML
    private void emptyCart() {
        buyer.emptyCart();
        displayCart();
    }
}