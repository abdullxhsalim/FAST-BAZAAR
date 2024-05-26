package com.example.controller;

import java.io.IOException;

import com.example.model.*;
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

public class RegisterBuyerSceneController {
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
        registerButton.setOnAction(e -> registerBuyer());
    }

    private void registerBuyer() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        int zip = Integer.parseInt(zipField.getText());
        String country = countryField.getText();
    
        Buyer newBuyer = new Buyer(name, email, password, phone, address, city, zip, country);
        System.out.println("Here is your profile:");
        System.out.println(newBuyer);
        com.example.view.Main.buyers.add(newBuyer);
        Buyer.updateBinaryFile(com.example.view.Main.buyers);
    
        messageLabel.setText("Registration successful!");
    
        loadScene("/com/example/view/BuyerInfoScene.fxml", newBuyer);
    }
    private void loadScene(String fxmlFile, Buyer buyer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
    
            BuyerInfoSceneController controller = loader.getController();
            controller.setBuyer(buyer);
    
            Scene scene = new Scene(root);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}