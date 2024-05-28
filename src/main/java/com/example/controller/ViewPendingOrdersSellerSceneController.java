package com.example.controller;

import com.example.model.Seller;
import com.example.model.SellerOrder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class ViewPendingOrdersSellerSceneController {
    private Seller seller;

    @FXML
    private ListView<SellerOrder> orderListView;
    @FXML
    private Button goBackButton;

    public void setSeller(Seller seller) {
        this.seller = seller;
        ObservableList<SellerOrder> orders = FXCollections.observableArrayList(seller.getPendingOrders());
        orderListView.setItems(orders);

        orderListView.setCellFactory(param -> new ListCell<SellerOrder>() {
            @Override
            protected void updateItem(SellerOrder order, boolean empty) {
                super.updateItem(order, empty);

                if (empty || order == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Text text = new Text("Order: " + order + "\nStatus: " + order.getStatus() + "\nOrder Placement Date: " + order.getOrderPlacementDate());
                    setGraphic(text);
                }
            }
        });

        goBackButton.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInSellerScene.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            LoggedInSellerSceneController controller = loader.getController();
            controller.setSeller(this.seller);
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        });
    }
}