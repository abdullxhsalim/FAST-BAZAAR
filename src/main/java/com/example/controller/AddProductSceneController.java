package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.example.model.Product;
import com.example.model.Seller;
import javafx.stage.FileChooser;

import java.io.File;

public class AddProductSceneController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField imageField;
    @FXML
    private TextField statusField;

    private Seller seller;

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @FXML
    private void addProduct() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        double price = Double.parseDouble(priceField.getText());
        String status = statusField.getText();
    
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        String image = selectedFile != null ? selectedFile.getPath() : "";
    
        Product product = new Product(name, description, price, seller, image, status);
        seller.addProduct(product);
    }
    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imageField.setText(selectedFile.getPath());
        }
    }
}