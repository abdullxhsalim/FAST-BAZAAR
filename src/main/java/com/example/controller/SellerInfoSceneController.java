package com.example.controller;

import com.example.model.Seller;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class SellerInfoSceneController {
    @FXML
    private Label nameLabel, emailLabel, phoneLabel, addressLabel, zipLabel, cityLabel, countryLabel;
    @FXML
    private Button goBackButton;

    public void initialize() {
        goBackButton.setOnAction(e -> loadScene("/com/example/view/SellerScene.fxml"));
    }

    public void setSeller(Seller seller) {
        nameLabel.setText("Name: " + seller.getName());
        emailLabel.setText("Email: " + seller.getEmail());
        phoneLabel.setText("Phone: " + seller.getPhone());
        addressLabel.setText("Address: " + seller.getAddress());
        zipLabel.setText("Zip Code: " + seller.getZip());
        cityLabel.setText("City: " + seller.getCity());
        countryLabel.setText("Country: " + seller.getCountry());
    }

    private void loadScene(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}