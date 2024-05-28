package com.example.controller;

import java.io.IOException;

import com.example.model.Buyer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class LoggedInBuyerSceneController {
    private Buyer buyer;
    @FXML
    private Label instructionLabel;
    @FXML
    private Button cartButton;
    @FXML
    private Button browseProductsButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button goBackButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button viewOrdersButton;

    public static Scene sceneObj;
    public static Buyer currentBuyer;

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
        instructionLabel.setText("Welcome, " + buyer.getName() + "!");
    }

    @FXML
    private void cart() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/ViewCartScene.fxml"));
            Parent root = loader.load();

            ViewCartSceneController controller = loader.getController();
            controller.setBuyer(buyer);

            Stage stage = (Stage) cartButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void browseProducts() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/BrowseProductsScene.fxml"));
            Parent root = loader.load();

            BrowseProductsSceneController controller = loader.getController();
            controller.setBuyer(buyer);

            Stage stage = (Stage) browseProductsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void profile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/ViewProfileBuyerScene.fxml"));
            Parent root = loader.load();

            ViewProfileBuyerSceneController controller = loader.getController();
            controller.setBuyer(buyer);

            Stage stage = (Stage) profileButton.getScene().getWindow();
            sceneObj = stage.getScene();
            currentBuyer = buyer;
            stage.setScene(new Scene(root));
            stage.setMinHeight(400);
            stage.setMinWidth(600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoginBuyerScene.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void viewOrders() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/ViewOrdersBuyerScene.fxml"));
            Parent root = loader.load();

            ViewOrdersBuyerSceneController controller = loader.getController();
            controller.setBuyer(buyer);

            Stage stage = (Stage) viewOrdersButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}