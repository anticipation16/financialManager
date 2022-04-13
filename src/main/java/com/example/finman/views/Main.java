package com.example.finman.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // getting loader and a pane for the first scene.
        // loader will then give a possibility to get related controller
        FXMLLoader homePaneLoader = new FXMLLoader(getClass().getResource("/fxml/main-view.fxml"));
        Parent homePane = homePaneLoader.load();
        Scene homeScene = new Scene(homePane);
        System.out.println(getClass().getResource("/css/pieChart.css").getFile());
        homeScene.getStylesheets().add(getClass().getResource("/css/pieChart.css").toExternalForm());
        stage.setTitle("Finance Manager");
        stage.setWidth(900);
        stage.setHeight(670);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setScene(homeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}