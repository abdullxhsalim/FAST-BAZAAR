package com.example.controller;

import java.io.IOException;

import com.example.exceptions.Exceptions;
import com.example.model.Seller;
import com.example.view.Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ViewProfileSellerSceneController {
    private Seller seller;
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
    private Button goBackButton;
    @FXML
    private Button editButton;
    @FXML
    private Label messageLabel;

    public void setSeller(Seller seller) {
        this.seller = seller;
        displaySellerInfo();
    }

    private void displaySellerInfo() {
        nameField.setText(seller.getName());
        emailField.setText(seller.getEmail());
        phoneField.setText(seller.getPhone());
        passwordField.setText(seller.getPassword());
        addressField.setText(seller.getAddress());
        cityField.setText(seller.getCity());
        zipField.setText(String.valueOf(seller.getZip()));
        countryField.setText(seller.getCountry());
    }

    @FXML
    private void editSellerInfo() {
        try {
            Exceptions.emailValidator(emailField.getText());
            Exceptions.nameValidator(nameField.getText());
            Exceptions.phoneValidator(phoneField.getText());
            Exceptions.zipValidator(zipField.getText());

            seller.setName(nameField.getText());
            seller.setEmail(emailField.getText());
            seller.setPhone(phoneField.getText());
            seller.setAddress(addressField.getText());
            seller.setCity(cityField.getText());
            seller.setZip(Integer.parseInt(zipField.getText()));
            seller.setCountry(countryField.getText());
            for (Seller b : Main.sellers) {
                if (b.equals(seller)) {
                    b.setName(seller.getName());
                    b.setEmail(seller.getEmail());
                    b.setPhone(seller.getPhone());
                    b.setAddress(seller.getAddress());
                    b.setCity(seller.getCity());
                    b.setZip(seller.getZip());
                    b.setCountry(seller.getCountry());
                }
            }
            Seller.updateBinaryFile(Main.sellers);
            messageLabel.setText("Profile updated successfully!");
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

    @FXML
    private void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInSellerScene.fxml"));
        Parent root = loader.load();
        LoggedInSellerSceneController controller = loader.getController();
        controller.setSeller(this.seller);
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}