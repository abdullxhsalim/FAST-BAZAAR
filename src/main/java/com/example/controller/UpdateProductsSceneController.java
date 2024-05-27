package com.example.controller;

import com.example.view.Main;
import com.example.model.Product;
import com.example.model.Seller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UpdateProductsSceneController {
    private Seller seller;

    @FXML
    private ImageView productImageView;
    @FXML
    private Button chooseImageButton;
    @FXML
    private ListView<Product> productListView;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productDescriptionField;
    @FXML
    private TextField productPriceField;
    @FXML
    private Button updateProductButton;
    @FXML
    private Button removeProductButton;
    @FXML
    private Button goBackButton;

    public void setSeller(Seller seller) {
        this.seller = seller;
        displaySellerProducts();
        productListView.getSelectionModel().selectFirst(); // Select the first product
    }

    private void displaySellerProducts() {
        productListView.getItems().setAll(seller.getProducts());
        productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> displayProductInfo(newValue));
    }

    private void displayProductInfo(Product product) {
        if (product != null) {
            String productInfo = String.format("Name: %s\nDescription: %s\nPrice: %.2f",
                    product.getName(), product.getDescription(), product.getPrice());
            productNameField.setText(product.getName());
            productDescriptionField.setText(product.getDescription());
            productPriceField.setText(String.valueOf(product.getPrice()));
        }
    }

    @FXML
    private void updateProduct() {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            selectedProduct.setName(productNameField.getText());
            selectedProduct.setDescription(productDescriptionField.getText());
            selectedProduct.setPrice(Double.parseDouble(productPriceField.getText()));
            productListView.refresh();
    
            for (Seller s : Main.sellers) {
                if (s.equals(seller)) {
                    s.setProducts(seller.getProducts());
                    break;
                }
            }
    
            Seller.updateBinaryFile(Main.sellers);
        }
    }
    
    @FXML
    private void removeProduct() {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            seller.removeProduct(selectedProduct);
            productListView.getItems().remove(selectedProduct);
    
            for (Seller s : Main.sellers) {
                if (s.equals(seller)) {
                    s.setProducts(seller.getProducts());
                    break;
                }
            }
            Seller.updateBinaryFile(Main.sellers);
        }
    }

    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            productImageView.setImage(image);

            Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                selectedProduct.setImagePath(selectedFile.getPath());

                for (Seller s : Main.sellers) {
                    if (s.equals(seller)) {
                        s.setProducts(seller.getProducts());
                        break;
                    }
                }

                Seller.updateBinaryFile(Main.sellers);
            }
        }
    }
    @FXML
    private void goBack() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/LoggedInSellerScene.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoggedInSellerSceneController controller = loader.getController();
        controller.setSeller(this.seller);
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}