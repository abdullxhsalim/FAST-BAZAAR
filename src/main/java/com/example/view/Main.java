package com.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.example.model.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Main extends Application {
    public static ArrayList<Buyer> buyers = new ArrayList<>();
    public static ArrayList<Seller> sellers = new ArrayList<>();
    public static ArrayList<Courier> couriers = new ArrayList<>();
    public static HashSet<Integer> productIDs = new HashSet<>();
    public static HashSet<Integer> buyerOrderIDs = new HashSet<>();
    public static HashSet<Integer> sellerOrderIDs = new HashSet<>();

    public static void buyerFileReady() {
        buyers = Buyer.readBuyersFromFile();
    }

    public static void sellerFileReady() {
        sellers = Seller.readSellersFromFile();
    }

    public static void courierFileReady() {
        couriers = Courier.readCouriersFromFile();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            buyerFileReady();
            sellerFileReady();
            courierFileReady();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/view/Scene1.fxml"));
            Scene scene = new Scene(root);
            Image logo = new Image(getClass().getResourceAsStream("/logo.jpg"));
            stage.getIcons().add(logo);
            stage.setScene(scene);
            stage.setTitle("FAST BAZAAR");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}