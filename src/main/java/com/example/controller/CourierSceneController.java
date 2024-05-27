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

public class CourierSceneController {
    @FXML
    private VBox layout;
    @FXML
    private Label instructionLabel;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;

    public void initialize() {
        instructionLabel.setText("Courier, what would you like to do?");
        registerButton.setOnAction(e -> loadScene("/com/example/view/RegisterCourierScene.fxml"));
        loginButton.setOnAction(e -> loadScene("/com/example/view/LoginCourierScene.fxml"));
        backButton.setOnAction(e -> loadScene("/com/example/view/Scene2.fxml"));
        exitButton.setOnAction(e -> {
            Stage stage = (Stage) layout.getScene().getWindow();
            stage.close();
        });
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