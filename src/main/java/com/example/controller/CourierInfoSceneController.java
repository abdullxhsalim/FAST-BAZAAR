package com.example.controller;

import com.example.model.Courier;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class CourierInfoSceneController {
    @FXML
    private Label nameLabel, emailLabel, phoneLabel, addressLabel, zipLabel, cityLabel, countryLabel;
    @FXML
    private Button goBackButton;

    public void initialize() {
        goBackButton.setOnAction(e -> loadScene("/com/example/view/CourierScene.fxml"));
    }

    public void setCourier(Courier courier) {
        nameLabel.setText(courier.getName());
        emailLabel.setText(courier.getEmail());
        phoneLabel.setText(courier.getPhone());
        addressLabel.setText(courier.getAddress());
        zipLabel.setText(""+courier.getZip());
        cityLabel.setText(courier.getCity());
        countryLabel.setText( courier.getCountry());
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