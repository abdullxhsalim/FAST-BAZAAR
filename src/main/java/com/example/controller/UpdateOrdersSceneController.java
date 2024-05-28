package com.example.controller;

import java.io.IOException;

import com.example.model.Courier;
import com.example.model.SellerOrder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UpdateOrdersSceneController {
    private Courier courier;

    @FXML
    private ListView<SellerOrder> orderListView;
    @FXML
    private Button goBackButton;

    public void setCourier(Courier courier) {
        this.courier = courier;
        updateOrderListView();
    }

    private void updateOrderListView() {
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
                    Text text = new Text(order.toString());
                    Button button = new Button("Deliver");
                    button.setAlignment(getAlignment());
                    button.setStyle("-fx-background-color: lime;");
                    button.setOnAction(event -> {
                        Alert alert = new Alert(AlertType.CONFIRMATION,
                                "Are you sure you want to mark this order as delivered?", ButtonType.YES,
                                ButtonType.NO);
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.YES) {
                            order.setStatus("Delivered");
                            courier.updateSellerOrderStatus(order);
                            updateOrderListView();
                        }
                    });
                    hBox.getChildren().addAll(text, button);
                    setGraphic(hBox);
                }
            }
        });
    }

    @FXML
    private void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInCourierScene.fxml"));
        Parent root = loader.load();
        LoggedInCourierSceneController controller = loader.getController();
        controller.setCourier(this.courier);
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}