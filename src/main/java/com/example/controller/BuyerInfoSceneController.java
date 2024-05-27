package com.example.controller;

import com.example.model.Buyer;
import com.example.model.Courier;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class BuyerInfoSceneController {
    @FXML
    private Label nameLabel, emailLabel, phoneLabel, addressLabel, zipLabel, cityLabel, countryLabel;
    @FXML
    private Button goBackButton;

    public void initialize() {
        goBackButton.setOnAction(e -> loadScene("/com/example/view/BuyerScene.fxml"));
    }

    public void setBuyer(Buyer buyer) {
        nameLabel.setText(buyer.getName());
        emailLabel.setText(buyer.getEmail());
        phoneLabel.setText(buyer.getPhone());
        addressLabel.setText(buyer.getAddress());
        zipLabel.setText(""+buyer.getZip());
        cityLabel.setText(buyer.getCity());
        countryLabel.setText( buyer.getCountry());
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