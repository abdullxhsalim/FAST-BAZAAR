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
import com.example.model.Buyer;

public class LoginBuyerSceneController {

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
    
        for (Buyer buyer : com.example.view.Main.buyers) {
            if (buyer.getEmail().equalsIgnoreCase(email) && buyer.getPassword().equals(password)) {
                messageLabel.setText("Login successful!");
                loadScene("/com/example/view/LoggedInBuyerScene.fxml", buyer);
                return;
            }
        }
        messageLabel.setText("Login failed! Go back or try again.");
    }

    @FXML
    private void goBack() {
        goBackButton.setOnAction(e -> loadScene("/com/example/view/BuyerScene.fxml"));
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
    private void loadScene(String fxmlFile, Buyer buyer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
    
            LoggedInBuyerSceneController controller = loader.getController();
            controller.setBuyer(buyer);
    
            Scene scene = new Scene(root);
            Stage stage = (Stage) layout.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}