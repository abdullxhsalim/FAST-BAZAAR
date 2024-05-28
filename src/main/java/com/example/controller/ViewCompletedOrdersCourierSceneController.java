package com.example.controller;

import com.example.model.Courier;
import com.example.model.SellerOrder;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewCompletedOrdersSceneController {
    private Courier courier;

    @FXML
    private ListView<SellerOrder> orderListView;

    public void setCourier(Courier courier) {
        this.courier = courier;
        ObservableList<SellerOrder> orders = FXCollections.observableArrayList(courier.getCompletedOrders());
        orderListView.setItems(orders);

        orderListView.setCellFactory(param -> new ListCell<SellerOrder>() {
            @Override
            protected void updateItem(SellerOrder order, boolean empty) {
                super.updateItem(order, empty);

                if (empty || order == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Text text = new Text("Order: " + order + "\nStatus: " + order.getStatus() + "\nOrder Placement Date: " + order.getOrderPlacementDate() + "\nDelivery Date: " + order.getDeliveryDate().toString());                    setGraphic(text);
                }
            }
        });
    }
}