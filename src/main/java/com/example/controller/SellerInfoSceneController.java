package com.example.controller;

import com.example.model.Courier;
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
        nameLabel.setText(seller.getName());
        emailLabel.setText(seller.getEmail());
        phoneLabel.setText(seller.getPhone());
        addressLabel.setText(seller.getAddress());
        zipLabel.setText(""+seller.getZip());
        cityLabel.setText(seller.getCity());
        countryLabel.setText( seller.getCountry());
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