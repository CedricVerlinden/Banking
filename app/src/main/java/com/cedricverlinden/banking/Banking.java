package com.cedricverlinden.banking;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Banking extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Banking");

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

        ScreenManager screenManager = new ScreenManager(root);

        screenManager.addScreen("Home", "fxml/HomeScreen.fxml");
        screenManager.addScreen("Profile", "fxml/ProfileScreen.fxml");

        screenManager.setScreen("Home");
    }

    public static void main(String[] args) {
        launch(args);
    }
}