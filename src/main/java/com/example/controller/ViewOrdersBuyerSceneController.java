package com.example.controller;

import com.example.model.Buyer;
import com.example.model.BuyerOrder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewOrdersBuyerSceneController {
    private Buyer buyer;

    @FXML
    private Button goBackButton;
    @FXML
    private ListView<BuyerOrder> ordersListView;

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
        displayOrders();
    }

    private void displayOrders() {
        ordersListView.getItems().clear();
        ordersListView.getItems().addAll(buyer.getOrders());
    }
    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInBuyerScene.fxml"));
            Parent root = loader.load();
            LoggedInBuyerSceneController controller = loader.getController();
            controller.setBuyer(this.buyer);
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}