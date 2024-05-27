package com.example.controller;

import com.example.model.Courier;
import com.example.model.SellerOrder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UpdateOrdersSceneController {
    private Courier courier;

    @FXML
    private ListView<SellerOrder> orderListView;

    public void setCourier(Courier courier) {
        this.courier = courier;
        ObservableList<SellerOrder> orders = FXCollections.observableArrayList(courier.getPendingOrders());
        orderListView.setItems(orders);

        orderListView.setCellFactory(param -> new ListCell<SellerOrder>() {
            @Override
            protected void updateItem(SellerOrder order, boolean empty) {
                super.updateItem(order, empty);

                if (empty || order == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    Text text = new Text("SellerOrder: " + order.toString());
                    Button button = new Button("Delivered");
                    button.setOnAction(event -> {
                        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to mark this order as delivered?", ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.YES) {
                            order.setStatus("Delivered");
                            courier.updateSellerOrderStatus(order);
                        }
                    });

                    hBox.getChildren().addAll(text, button);
                    setGraphic(hBox);
                }
            }
        });
    }
}