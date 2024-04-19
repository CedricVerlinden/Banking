package com.cedricverlinden.banking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);

        stage.setTitle("Banking");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icons/icon.png")));
        
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}