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
		Scene scene = new Scene(root, 1280, 720);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();

		ScreenManager screenManager = new ScreenManager(root);

		// Account screens
		screenManager.addScreen("Login", "fxml/LoginScreen.fxml");
		screenManager.addScreen("Register", "fxml/RegisterScreen.fxml");

		// Dashboard screens
		screenManager.addScreen("Home", "fxml/HomeScreen.fxml");

		screenManager.setScreen("Login");
	}

	public static void main(String[] args) {
		launch(args);
	}
}