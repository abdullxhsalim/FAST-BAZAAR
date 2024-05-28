package com.example.controller;

import com.example.model.Seller;
import com.example.model.SellerOrder;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewCompletedOrdersSellerScene {
    private Seller seller;

    @FXML
    private ListView<SellerOrder> orderListView;
    @FXML
    private Button goBackButton;

    public void setSeller(Seller seller) {
        this.seller = seller;
        ObservableList<SellerOrder> orders = FXCollections.observableArrayList(seller.getCompletedOrders());
        orderListView.setItems(orders);

        orderListView.setCellFactory(param -> new ListCell<SellerOrder>() {
            @Override
            protected void updateItem(SellerOrder order, boolean empty) {
                super.updateItem(order, empty);

                if (empty || order == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Text text = new Text("Order: " + order + "\nStatus: " + order.getStatus() + "\nOrder Placement Date: " + order.getOrderPlacementDate() + "\nDelivery Date: " + order.getDeliveryDate().toString());
                    setGraphic(text);
                }
            }
        });
    }
}