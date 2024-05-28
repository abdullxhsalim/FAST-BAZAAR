package com.example.controller;

import com.example.model.Courier;
import com.example.model.SellerOrder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewCompletedOrdersCourierSceneController {
    private Courier courier;
    @FXML
    private Button goBackButton;

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
    @FXML
    private void goBack() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInCourierScene.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoggedInCourierSceneController controller = loader.getController();
        controller.setCourier(this.courier);
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}