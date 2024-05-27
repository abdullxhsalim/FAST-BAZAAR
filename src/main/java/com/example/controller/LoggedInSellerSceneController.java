package com.example.controller;

import com.example.model.Seller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoggedInSellerSceneController {
    @FXML
    private Label instructionLabel;
    private Seller seller;
    public void setSeller(Seller seller) {
        this.seller = seller;
        instructionLabel.setText("Welcome, " + seller.getName() + "!");
    }
    @FXML
    private Button addProductButton;

    @FXML
    private Button viewAndUpdateProductsButton;

    @FXML
    private Button removeProductsButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Button exitButton;
    public static Scene sceneObj;
    public static Seller currentSeller;

    @FXML
    private void initialize() {
        // Initialization code can go here
    }

    @FXML
    private void addProduct() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/AddProductScene.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AddProductSceneController controller = loader.getController();
        controller.setSeller(seller);

        Stage stage = (Stage) profileButton.getScene().getWindow();
        sceneObj = stage.getScene();
        currentSeller = seller;
        stage.setScene(new Scene(root));
        stage.setMinHeight(400);
        stage.setMinWidth(600);
    }

    @FXML
    private void viewAndUpdateProducts() {
        // Code to view and update products
    }

    @FXML
    private void removeProducts() {
        // Code to remove products
    }
    @FXML
    private void profile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/ViewProfileSellerScene.fxml"));
            Parent root = loader.load();

            ViewProfileSellerSceneController controller = loader.getController();
            controller.setSeller(seller);

            Stage stage = (Stage) profileButton.getScene().getWindow();
            sceneObj = stage.getScene();
            currentSeller = seller;
            stage.setScene(new Scene(root));
            stage.setMinHeight(400);
            stage.setMinWidth(600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack() {
        // Code to go back
    }

    @FXML
    private void exit() {
        // Code to exit the program
    }
}
