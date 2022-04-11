package com.example.finman.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    public static void switchScene(ActionEvent actionEvent, String fxmlLocation) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(SceneController.class.getResource(fxmlLocation));
        Parent pane = loader.load();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * For switching scenes to those fxml files that lack a fx:controller attribute.
     */
    public static void switchScene(ActionEvent actionEvent, String fxmlLocation, Controller controller) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(SceneController.class.getResource(fxmlLocation));
        loader.setController(controller);
        Parent pane = loader.load();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
