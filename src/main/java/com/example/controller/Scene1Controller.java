package com.example.controller;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;
import java.io.IOException;

public class Scene1Controller {
    @FXML
    private ImageView logo;
    @FXML
    private Label label;

    public void initialize() {
        logo.setImage(new Image(getClass().getResourceAsStream("/logo.jpg")));

        FadeTransition fadeTransitionLogo = new FadeTransition(Duration.seconds(1), logo);
        fadeTransitionLogo.setFromValue(0.0);
        fadeTransitionLogo.setToValue(1.0);

        ScaleTransition scaleTransitionLogo = new ScaleTransition(Duration.seconds(2), logo);
        scaleTransitionLogo.setFromX(1.5);
        scaleTransitionLogo.setFromY(1.5);
        scaleTransitionLogo.setToX(1.0);
        scaleTransitionLogo.setToY(1.0);

        FadeTransition fadeTransitionLabel = new FadeTransition(Duration.seconds(1), label);
        fadeTransitionLabel.setFromValue(0.0);
        fadeTransitionLabel.setToValue(1.0);

        ScaleTransition scaleTransitionLabel = new ScaleTransition(Duration.seconds(1), label);
        scaleTransitionLabel.setFromX(1.1);
        scaleTransitionLabel.setFromY(1.1);
        scaleTransitionLabel.setToX(1.0);
        scaleTransitionLabel.setToY(1.0);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));

        ParallelTransition parallelTransition = new ParallelTransition(
            fadeTransitionLogo,
            scaleTransitionLogo,
            fadeTransitionLabel,
            scaleTransitionLabel
        );

        SequentialTransition sequentialTransition = new SequentialTransition(
            parallelTransition,
            pause
        );

        sequentialTransition.setOnFinished(e -> loadScene("/com/example/view/Scene2.fxml"));
        sequentialTransition.play();
    }

    private void loadScene(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) logo.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}