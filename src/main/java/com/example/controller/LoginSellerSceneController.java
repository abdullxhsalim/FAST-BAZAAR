package com.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import com.example.model.Seller;

public class LoginSellerSceneController {

    @FXML
    public Label instructionLabel;
    @FXML
    private Button loginButton;
    @FXML
    private VBox layout;
    @FXML
    private Button goBackButton;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void login() {
        String email = emailField.getText();
        String password = passwordField.getText();

        for (Seller seller : com.example.view.Main.sellers) {
            if (seller.getEmail().equalsIgnoreCase(email) && seller.getPassword().equals(password)) {
                messageLabel.setText("Login successful!");
                loadScene("/com/example/view/LoggedInSellerScene.fxml", seller);
                return;
            }
        }
        messageLabel.setText("Login failed! Go back or try again.");
    }

    @FXML
    private void goBack() {
        goBackButton.setOnAction(e -> loadScene("/com/example/view/SellerScene.fxml"));
    }

    private void loadScene(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) layout.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadScene(String fxmlFile, Seller seller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            LoggedInSellerSceneController controller = loader.getController();
            controller.setSeller(seller);

            Scene scene = new Scene(root);
            Stage stage = (Stage) layout.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}