package com.example.controller;

import com.example.model.Buyer;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.model.Product;
import com.example.model.Seller;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AddProductSceneController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField priceField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button chooseImageButton;
    @FXML
    private Button addProductButton;
    @FXML
    private Button goBackButton;
    private Seller seller;

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @FXML
    private void addProduct() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        double price = Double.parseDouble(priceField.getText());
        String image = chooseImageButton.getText();
        Product product = new Product(name, description, price, seller, image);
        for (Seller s : Main.sellers) {
            if (seller.equals(s)) {
                s.addProduct(product);
                break;
            }
        }
        Seller.updateBinaryFile(Main.sellers);
        messageLabel.setText("Product added successfully!");
        addProductButton.setDisable(true);
        addProductButton.setVisible(false);
    }

    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String imagePath = selectedFile.getAbsolutePath().replace("\\", "/");
            String imageUrl = "file:/" + imagePath;
            chooseImageButton.setText(imageUrl);
        }
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInSellerScene.fxml"));
            Parent root = loader.load();
            LoggedInSellerSceneController controller = loader.getController();
            controller.setSeller(this.seller);
            Stage stage = (Stage) addProductButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
