package com.example.sh;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SettingsController{
    @FXML
    private AnchorPane SettingsAnchor;
    @FXML
    private Text recordText;
    @FXML
    private Text cherryText;

    @FXML
    private void SettingsScene(MouseEvent event) throws IOException {
        SceneFactory scene = new SceneFactory();
        Switcher switch1 = scene.createSwitcher("Switch");
        switch1.Switch(SettingsAnchor, "StickStart.fxml");
    }

    @FXML
    private void RandomOn(MouseEvent event) {
        Randomizer.setRandom_char(true);
    }

    @FXML
    private void RandomOff(MouseEvent event) {
        Randomizer.setRandom_char(false);
    }

    @FXML
    public void initialize() {
        try {
            // Load the record from the file
            int recordValue = loadRecord();

            // Display the record value
            recordText.setText("Record: " + recordValue);

            // Load the cherry value from the file
            int cherryValue = loadCherry();

            // Display the cherry value
            cherryText.setText("Cherry: " + cherryValue);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // Load the record from the file
    private int loadRecord() throws IOException {
        return loadValueFromFile("record.txt");
    }

    // Load the cherry value from the file
    private int loadCherry() throws IOException {
        return loadValueFromFile("cherry.txt");
    }

    private int loadValueFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {
            throw e;
        }
    }
}
