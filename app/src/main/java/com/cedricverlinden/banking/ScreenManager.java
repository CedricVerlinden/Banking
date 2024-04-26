package com.cedricverlinden.banking;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cedricverlinden.banking.controllers.BaseController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

public class ScreenManager {
    private final StackPane root;
    private final Map<String, Pair<Node, Object>> screenMap = new HashMap<>();

    public ScreenManager(StackPane root) {
        this.root = root;
    }

    public void addScreen(String name, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node screenContent = loader.load();
            Object controller = loader.getController();
            screenMap.put(name, new Pair<>(screenContent, controller));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setScreen(String name) {
        if (!screenMap.containsKey(name)) {
            return;
        }

        root.getChildren().clear();
        root.getChildren().add(screenMap.get(name).getKey());

        Object controller = screenMap.get(name).getValue();
        System.out.println(controller.getClass().getName());
        if (controller instanceof BaseController) {
            System.out.println("Does extend it..");
            ((BaseController) controller).setScreenManager(this);
        }
    }
}
