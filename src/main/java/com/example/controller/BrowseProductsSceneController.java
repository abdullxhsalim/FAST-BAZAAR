package com.example.controller;

import com.example.model.Buyer;
import com.example.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class BrowseProductsSceneController {

    private Buyer buyer;
    @FXML
    private VBox productsContainer;
    @FXML
    private TextField searchBox;
    @FXML
    private Label messageLabel;

    @FXML
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @FXML
    private Button goBackButton;

    @FXML
    public void initialize() {

        goBackButton.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInBuyerScene.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException f) {
                f.printStackTrace();
            }
            LoggedInBuyerSceneController e = loader.getController();
            e.setBuyer(buyer);
            Scene scene = goBackButton.getScene();
            scene.setRoot(root);
        });
        searchBox.setOnAction(event -> {
            String searchText = searchBox.getText();
            productsContainer.getChildren().clear();
            for (Product product : Buyer.getSearchedProducts(searchText)) {
                if (product.getName() != null && !product.getName().isEmpty() &&
                        product.getDescription() != null && !product.getDescription().isEmpty() &&
                        product.getImagePath() != null && !product.getImagePath().isEmpty() &&
                        product.getSeller() != null && product.getSeller().getName() != null
                        && !product.getSeller().getName().isEmpty()) {
                    messageLabel.setText("Search results for: " + searchText);
                    HBox productBox = new HBox(10);
                    ImageView productImage = new ImageView(new Image(product.getImagePath()));
                    productImage.setFitWidth(100);
                    productImage.setPreserveRatio(true);
                    Label productNameLabel = new Label("Name: " + product.getName());
                    Label productDescriptionLabel = new Label("Description: " + product.getDescription());
                    Label productPriceLabel = new Label("Price: " + String.valueOf(product.getPrice()));
                    Label productSellerLabel = new Label("Seller: " + product.getSeller().getName());
                    Spinner<Integer> quantitySpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
                    Button addToCartButton = new Button("Add to Cart");
                    addToCartButton.setVisible(false);
                    quantitySpinner.valueProperty()
                            .addListener((obs, oldValue, newValue) -> addToCartButton.setVisible(newValue > 0));
                    addToCartButton.setOnAction(f -> {
                        for (int i = 0; i < quantitySpinner.getValue(); i++) {
                            buyer.addToCart(product);
                        }
                    });

                    HBox spinnerAndButtonBox = new HBox(quantitySpinner, addToCartButton);
                    VBox productDetailsBox = new VBox(productNameLabel, productDescriptionLabel, productPriceLabel,
                            productSellerLabel, spinnerAndButtonBox);
                    productBox.getChildren().addAll(productImage, productDetailsBox);
                    productsContainer.getChildren().add(productBox);
                }
            }
        });
        for (Product product : Buyer.getAllProducts()) {
            if (product.getName() != null && !product.getName().isEmpty() &&
                    product.getDescription() != null && !product.getDescription().isEmpty() &&
                    product.getImagePath() != null && !product.getImagePath().isEmpty() &&
                    product.getSeller() != null && product.getSeller().getName() != null
                    && !product.getSeller().getName().isEmpty()) {
                HBox productBox = new HBox(10);
                ImageView productImage = new ImageView(new Image(product.getImagePath()));
                productImage.setFitWidth(100);
                productImage.setPreserveRatio(true);
                Label productNameLabel = new Label("Name: " + product.getName());
                Label productDescriptionLabel = new Label("Description: " + product.getDescription());
                Label productPriceLabel = new Label("Price: " + String.valueOf(product.getPrice()));
                Label productSellerLabel = new Label("Seller: " + product.getSeller().getName()); 
                Spinner<Integer> quantitySpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
                Button addToCartButton = new Button("Add to Cart");
                addToCartButton.setVisible(false);
                quantitySpinner.valueProperty()
                        .addListener((obs, oldValue, newValue) -> addToCartButton.setVisible(newValue > 0));
                addToCartButton.setOnAction(event -> {
                    for (int i = 0; i < quantitySpinner.getValue(); i++) {
                        buyer.addToCart(product);
                    }
                });

                HBox spinnerAndButtonBox = new HBox(quantitySpinner, addToCartButton);
                VBox productDetailsBox = new VBox(productNameLabel, productDescriptionLabel, productPriceLabel,
                        productSellerLabel, spinnerAndButtonBox);
                productBox.getChildren().addAll(productImage, productDetailsBox);
                productsContainer.getChildren().add(productBox);
            }
        }

    }
}