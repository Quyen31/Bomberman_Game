package com.example.bomberman_game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ViewManager;

import java.io.IOException;
import java.net.URISyntaxException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        ViewManager manager = new ViewManager();
        stage = manager.getMainStage();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}