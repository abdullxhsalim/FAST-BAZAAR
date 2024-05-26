package com.example.controller;

import com.example.model.Buyer;
import com.example.view.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewProfileBuyerSceneController {
    private Buyer buyer;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
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
    private Button editButton;
    @FXML
    private Label messageLabel;

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
        displayBuyerInfo();
    }

    private void displayBuyerInfo() {
        nameField.setText(buyer.getName());
        emailField.setText(buyer.getEmail());
        phoneField.setText(buyer.getPhone());
        addressField.setText(buyer.getAddress());
        cityField.setText(buyer.getCity());
        zipField.setText(String.valueOf(buyer.getZip()));
        countryField.setText(buyer.getCountry());
    }

    @FXML
    private void editBuyerInfo() {
        buyer.setName(nameField.getText());
        buyer.setEmail(emailField.getText());
        buyer.setPhone(phoneField.getText());
        buyer.setAddress(addressField.getText());
        buyer.setCity(cityField.getText());
        buyer.setZip(Integer.parseInt(zipField.getText()));
        buyer.setCountry(countryField.getText());
        for (Buyer b : Main.buyers) {
            if (b.equals(buyer)) {
                b.setName(buyer.getName());
                b.setEmail(buyer.getEmail());
                b.setPhone(buyer.getPhone());
                b.setAddress(buyer.getAddress());
                b.setCity(buyer.getCity());
                b.setZip(buyer.getZip());
                b.setCountry(buyer.getCountry());
            }
        }
        Buyer.updateBinaryFile(Main.buyers);
        messageLabel.setText("Profile updated successfully!");
    }
}