package com.example.controller;

import java.io.IOException;

import com.example.exceptions.Exceptions;
import com.example.model.Courier;
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

public class ViewProfileCourierSceneController {
    private Courier courier;
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

    public void setCourier(Courier courier) {
        this.courier = courier;
        displayCourierInfo();
    }

    private void displayCourierInfo() {
        nameField.setText(courier.getName());
        emailField.setText(courier.getEmail());
        phoneField.setText(courier.getPhone());
        passwordField.setText(courier.getPassword());
        addressField.setText(courier.getAddress());
        cityField.setText(courier.getCity());
        zipField.setText(String.valueOf(courier.getZip()));
        countryField.setText(courier.getCountry());
    }

    @FXML
    private void editCourierInfo() {
        try {
            Exceptions.emailValidator(emailField.getText());
            Exceptions.nameValidator(nameField.getText());
            Exceptions.phoneValidator(phoneField.getText());
            Exceptions.zipValidator(zipField.getText());

            courier.setName(nameField.getText());
            courier.setEmail(emailField.getText());
            courier.setPhone(phoneField.getText());
            courier.setAddress(addressField.getText());
            courier.setCity(cityField.getText());
            courier.setZip(Integer.parseInt(zipField.getText()));
            courier.setCountry(countryField.getText());
            for (Courier c : Main.couriers) {
                if (c.equals(courier)) {
                    c.setName(courier.getName());
                    c.setEmail(courier.getEmail());
                    c.setPhone(courier.getPhone());
                    c.setAddress(courier.getAddress());
                    c.setCity(courier.getCity());
                    c.setZip(courier.getZip());
                    c.setCountry(courier.getCountry());
                }
            }
            Courier.updateBinaryFile(Main.couriers);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInCourierScene.fxml"));
        Parent root = loader.load();
        LoggedInCourierSceneController controller = loader.getController();
        controller.setCourier(this.courier);
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}