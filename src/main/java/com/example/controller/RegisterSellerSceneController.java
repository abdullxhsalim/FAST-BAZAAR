package com.example.controller;

import java.io.IOException;

import com.example.exceptions.Exceptions;
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

public class RegisterSellerSceneController {
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
    @FXML
    private Button goBackButton;

    public void initialize() {
        registerButton.setOnAction(e -> registerSeller());
        goBackButton.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/view/SellerScene.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) goBackButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void registerSeller() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String country = countryField.getText();

        try {
            Exceptions.emailValidator(email);
            Exceptions.nameValidator(name);
            Exceptions.phoneValidator(phone);
            Exceptions.zipValidator(zipField.getText());

            int zip = Integer.parseInt(zipField.getText());

            Seller newSeller = new Seller(name, email, password, phone, address, city, zip, country);
            System.out.println("Here is your profile:");
            System.out.println(newSeller);
            com.example.view.Main.sellers.add(newSeller);
            Seller.updateBinaryFile(com.example.view.Main.sellers);

            messageLabel.setText("Registration successful!");

            loadScene("/com/example/view/SellerInfoScene.fxml", newSeller);
        } catch (Exceptions.InvalidEmailException e) {
            emailField.setStyle("-fx-text-fill: red;");
            messageLabel.setText(e.toString());
        } catch (Exceptions.InvalidNameException e) {
            nameField.setStyle("-fx-text-fill: red;");
            messageLabel.setText(e.toString());
        } catch (Exceptions.InvalidPhoneException e) {
            phoneField.setStyle("-fx-text-fill: red;");
            messageLabel.setText(e.toString());
        } catch (Exceptions.InvalidZipException e) {
            zipField.setStyle("-fx-text-fill: red;");
            messageLabel.setText(e.toString());
        }
    }

    private void loadScene(String fxmlFile, Seller seller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            SellerInfoSceneController controller = loader.getController();
            controller.setSeller(seller);

            Scene scene = new Scene(root);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}