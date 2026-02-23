package com.example.sh;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

//Polymorphism in context SceneSwitch
public class SceneSwitch2 implements Switcher {


    @Override
    public void Switch(AnchorPane currentAnchorPane, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        // Access the controller of the loaded FXML
        GameController controller = loader.getController();

        // Pass the initial score to the controller
        controller.initParams(0);

        currentAnchorPane.getChildren().setAll(root);
    }
}
class SceneSwitch3 implements Switcher {


    @Override
    public void Switch(AnchorPane currentAnchorPane, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        // Access the controller of the loaded FXML
        GameController controller = loader.getController();

        // Read the score from the file
        int initialScore = readScoreFromFile();

        // Pass the initial score to the controller
        controller.initParams(initialScore);

        currentAnchorPane.getChildren().setAll(root);
    }
    private int readScoreFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("score.txt"));
            String line = reader.readLine();
            reader.close();

            // Parse the score from the file
            return Integer.parseInt(line);
        } catch (IOException | NumberFormatException e) {
            // Handle exceptions (e.g., file not found, invalid content)
            e.printStackTrace();
            return 0; // Default to 0 if there is an issue
        }
    }
}

