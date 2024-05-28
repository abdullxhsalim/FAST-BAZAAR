package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class Scene2Controller {
    @FXML
    private VBox layout;
    @FXML
    private Label instructionLabel;

    @FXML
    private Button buyerButton;
    @FXML
    private Button sellerButton;
    @FXML
    private Button courierButton;
    // @FXML
    // private Button adminButton;

    public void initialize() {
        instructionLabel.setText("Who is using the program?");
        buyerButton.setOnAction(e -> loadScene("/com/example/view/BuyerScene.fxml"));
        sellerButton.setOnAction(e -> loadScene("/com/example/view/SellerScene.fxml"));
        courierButton.setOnAction(e -> loadScene("/com/example/view/CourierScene.fxml"));
        // adminButton.setOnAction(e -> loadScene("/com/example/view/AdminScene.fxml"));
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
}