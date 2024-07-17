package com.example.sh;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;
//interface
interface Switcher {
    void Switch(AnchorPane currentAnchorPane, String fxml) throws IOException;
}
public class SceneSwitch implements Switcher{



     public void Switch(AnchorPane currentAnchorPane, String fxml) throws IOException {
        AnchorPane nextAnchorPane= FXMLLoader.load(Objects.requireNonNull(StickStart.class.getResource(fxml)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }}

//Design Patten Factory
class SceneFactory {
    public static Switcher createSwitcher(String switcherType) {
        if ("Switch".equalsIgnoreCase(switcherType)) {
            return new SceneSwitch();
        } else if ("Switch2".equalsIgnoreCase(switcherType)) {
            return new SceneSwitch2();
        }
        else if ("Switch3".equalsIgnoreCase(switcherType)) {
            return new SceneSwitch3();
        }
        // Handle other cases or return a default implementation
        return new SceneSwitch();
    }
}