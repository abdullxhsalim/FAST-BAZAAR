package com.example.controller;

import java.io.IOException;

import com.example.model.*;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class RegisterCourierSceneController {
    @FXML
    private VBox layout;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField zipField;
    @FXML
    private TextField countryField;
    @FXML
    private Button registerButton;
    @FXML
    private Label messageLabel;

    public void initialize() {
        registerButton.setOnAction(e -> registerCourier());
    }

    private void registerCourier() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        int zip = Integer.parseInt(zipField.getText());
        String country = countryField.getText();

        Courier newCourier = new Courier(name, email, password, phone, address, city, zip, country);
        System.out.println("Here is your courier profile:");
        System.out.println(newCourier);
        Main.couriers.add(newCourier);
        Courier.updateBinaryFile(com.example.view.Main.couriers);
        messageLabel.setText("Registration successful!");
        loadScene("/com/example/view/CourierInfoScene.fxml", newCourier);
    }
    private void loadScene(String fxmlFile, Courier courier) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            CourierInfoSceneController controller = loader.getController();
            controller.setCourier(courier);

            Scene scene = new Scene(root);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}