package com.example.controller;

import java.io.IOException;

import com.example.model.Courier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class LoggedInCourierSceneController {
    private Courier Courier;
    @FXML
    private Label instructionLabel;
    @FXML
    private Button updateOrdersButton;
    @FXML
    private Button viewCompletedOrdersButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button goBackButton;
    @FXML
    private Button exitButton;

    public static Scene sceneObj;
    public static Courier currentCourier;

    public void setCourier(Courier Courier) {
        this.Courier = Courier;
        instructionLabel.setText("Welcome, " + Courier.getName() + "!");
    }

    @FXML
    private void updateOrders() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/UpdateOrdersScene.fxml"));
            Parent root = loader.load();

            UpdateOrdersSceneController controller = loader.getController();
            controller.setCourier(Courier);

            Stage stage = (Stage) updateOrdersButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void profile() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/view/ViewProfileCourierScene.fxml"));
            Parent root = loader.load();

            ViewProfileCourierSceneController controller = loader.getController();
            controller.setCourier(Courier);

            Stage stage = (Stage) profileButton.getScene().getWindow();
            sceneObj = stage.getScene();
            currentCourier = Courier;
            stage.setScene(new Scene(root));
            stage.setMinHeight(400);
            stage.setMinWidth(600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewCompletedOrders() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/ViewCompletedOrdersCourierScene.fxml"));
            Parent root = loader.load();

            ViewCompletedOrdersCourierSceneController controller = loader.getController();
            controller.setCourier(Courier);

            Stage stage = (Stage) viewCompletedOrdersButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void goBack () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoginCourierScene.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit () {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}