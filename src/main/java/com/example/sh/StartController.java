package com.example.sh;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StartController {
    @FXML
    private AnchorPane StartAnchor;
    @FXML
    private void MainScene(MouseEvent event) throws IOException {
        SceneFactory scene=new SceneFactory();
        Switcher switch1=scene.createSwitcher("Switch");

        switch1.Switch(StartAnchor,"StickStart.fxml");


    }
    //To start New Game
    @FXML
    private void StartGame(MouseEvent event) throws IOException {
        SceneFactory scene=new SceneFactory();
        Switcher switch1=scene.createSwitcher("Switch2");
        switch1.Switch(StartAnchor,"game.fxml");


    }
    //To Load Saved Game
    @FXML
    private void LoadGame(MouseEvent event) throws IOException {
        SceneFactory scene=new SceneFactory();
        Switcher switch1=scene.createSwitcher("Switch3");
        switch1.Switch(StartAnchor,"game.fxml");


    }

}
