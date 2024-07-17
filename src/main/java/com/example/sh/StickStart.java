package com.example.sh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StickStart extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Object fxmlLoader =FXMLLoader.load(getClass().getResource("StickStart.fxml"));

        stage.setTitle("StickHero");
        Randomizer.setRandom_char(true);
        stage.setScene(new Scene((Parent) fxmlLoader));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}