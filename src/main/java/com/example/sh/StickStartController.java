package com.example.sh;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StickStartController {
    @FXML
    private AnchorPane mainanchor;
    @FXML
    private void handleExitButton() {
        // Add code to exit the application gracefully
        System.out.println("Exit button clicked!");
        Platform.exit(); // This ensures a graceful shutdown
    }
    //To go Start Game
    @FXML
    private void StartScene(MouseEvent event) throws IOException {
        SceneFactory scene=new SceneFactory();
        Switcher switch1=scene.createSwitcher("Switch");
        switch1.Switch(mainanchor,"start.fxml");

    }
    //To Open Settings
    @FXML
    private void SettingsScene(MouseEvent event) throws IOException {
        SceneFactory scene=new SceneFactory();
        Switcher switch1=scene.createSwitcher("Switch");
        switch1.Switch(mainanchor,"settings.fxml");

    }


}