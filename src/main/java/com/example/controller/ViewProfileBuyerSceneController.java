package com.example.controller;

import com.example.exceptions.Exceptions;
import com.example.model.Buyer;
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

import java.io.IOException;

public class ViewProfileBuyerSceneController {
    private Buyer buyer;
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

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
        displayBuyerInfo();
    }

    private void displayBuyerInfo() {
        nameField.setText(buyer.getName());
        emailField.setText(buyer.getEmail());
        phoneField.setText(buyer.getPhone());
        passwordField.setText(buyer.getPassword());
        addressField.setText(buyer.getAddress());
        cityField.setText(buyer.getCity());
        zipField.setText(String.valueOf(buyer.getZip()));
        countryField.setText(buyer.getCountry());
    }

    @FXML
    private void editBuyerInfo() {
        try {
            Exceptions.emailValidator(emailField.getText());
            Exceptions.nameValidator(nameField.getText());
            Exceptions.phoneValidator(phoneField.getText());
            Exceptions.zipValidator(zipField.getText());

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInBuyerScene.fxml"));
        Parent root = loader.load();
        LoggedInBuyerSceneController controller = loader.getController();
        controller.setBuyer(this.buyer);
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}