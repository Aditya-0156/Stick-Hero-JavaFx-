package com.example.sh;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static java.lang.Math.ceil;

//Thread is used along with CountDownLatch
public class GameController extends Thread implements ParametrizedController{
    @Override
    public void initParams(Object... params) {
        if (params.length > 0 && params[0] instanceof Integer) {
            this.score = (Integer) params[0];

        }
        start();
    }
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private int Cherry_Count=0;
    private Text scoreText;
    private int score;
    private Cherry cherry;

    private GameRectangle newRectangle;
    private int revive=0;

    @FXML
    private AnchorPane GameAnchor;
    private boolean GameContinue;
    private String projectDir = System.getProperty("user.dir");
    private String[] defaultchar = {"wizard_run_1.png", "wizard_run_2.png", "wizard_run_3.png", "wizard_run_4.png", "wizard_run_5.png", "wizard_run_6.png", "wizard_run_7.png", "wizard_run_8.png"};
    private String[] Archer = {"archer_run_1.png", "archer_run_2.png", "archer_run_3.png", "archer_run_4.png", "archer_run_5.png", "archer_run_6.png", "archer_run_7.png", "archer_run_8.png"};
    private String[] Swordsman = {"swordsman_run_1.png", "swordsman_run_2.png", "swordsman_run_3.png", "swordsman_run_4.png", "swordsman_run_5.png", "swordsman_run_6.png", "swordsman_run_7.png", "swordsman_run_8.png"};

    private Character wizard;
    private Stick stick;
    @FXML
    private ImageView characterImageView;
    private Timeline growTimeline;
    private Text timerText;
    private Timeline countdownTimeline;
    private GameRectangle StartPlatform;

    @FXML
    public void initialize() {
        if(Randomizer.isRandom_Char()){
            defaultchar=getRandomCharacterPaths();
        }
        initializeScoreText();
        initializeTimer();
    }
    private String[] getRandomCharacterPaths() {

        double randomValue = Math.random();

        if (randomValue < 0.33) {
            return Swordsman;
        } else if (randomValue < 0.66) {
            return Archer;
        } else {
            return defaultchar;
        }
    }

    private void initializeTimer() {
        // Initialize the countdownTimeline and timerText
        countdownTimeline = new Timeline();
        timerText = new Text("5");
        timerText.setFont(Font.font(100));
        timerText.setFill(Color.WHITE);
        timerText.setX(300); // Adjust this value as needed
        timerText.setY(100);
        GameAnchor.getChildren().add(timerText);
    }
    private void initializeScoreText() {
        // Initialize the scoreText
        scoreText = new Text("Score: 0");
        scoreText.setFont(Font.font(30));
        scoreText.setFill(Color.WHITE);
        scoreText.setX(150);
        scoreText.setY(100);
        GameAnchor.getChildren().add(scoreText);
    }


    @FXML
    public void start() {
        Media media = new Media(getClass().getResource("/victory.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        Media media2 = new Media(getClass().getResource("/game_over.mp3").toExternalForm());
        mediaPlayer2 = new MediaPlayer(media2);
        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : GameAnchor.getChildren()) {
            if (node instanceof GameRectangle || node instanceof Stick || node instanceof Text || node instanceof Cherry) {
                nodesToRemove.add(node);
            }
        }
        GameAnchor.getChildren().removeAll(nodesToRemove);

        // Stop and clear the existing countdownTimeline if it's running
        if (countdownTimeline != null) {
            countdownTimeline.stop();
            countdownTimeline.getKeyFrames().clear();
        }

        wizard = new Character(characterImageView, defaultchar);
        StartPlatform = new GameRectangle(0, 45);
        GameAnchor.getChildren().add(StartPlatform);
        newRectangle = createRandomRectangle();
        GameAnchor.getChildren().add(newRectangle);
        if(score%3==0){
        cherry = new Cherry(getClass().getResource("/cherry.png").toExternalForm());
        cherry.setFitWidth(50); // Adjust the width as needed
        cherry.setFitHeight(50); // Adjust the height as needed
        cherry.createCherry(newRectangle.getX()+( newRectangle.getWidth() / 2), 290);
        GameAnchor.getChildren().add(cherry);}
        // Create a Stick instance and add it to GameAnchor
        stick = Stick.getInstance(35, 330);

        GameAnchor.getChildren().add(stick);

        // Update existing timerText properties if it is not null
        if (timerText != null) {
            timerText.setText("5");
            timerText.setX(300); // Adjust this value as needed
            GameAnchor.getChildren().add(timerText);
        }
        // Update existing scoreText properties if it is not null
        if (scoreText != null) {
            scoreText.setText("Score: " + score);
            scoreText.setX(150); // Adjust this value as needed
            GameAnchor.getChildren().add(scoreText);
        }

        // Reset countdownTimeline
        countdownTimeline = new Timeline();

        CountDownLatch latch = new CountDownLatch(1);

        countdownTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(5), e -> {
                    timerText.setText("0");
                    GameContinue = true;
                    latch.countDown(); // Release the latch when the timer is done
                },
                        new KeyValue(timerText.textProperty(), "0"))
        );
        for (int i = 1; i <= 5; i++) {
            countdownTimeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(i), new KeyValue(timerText.textProperty(), String.valueOf(5 - i)))
            );
        }

        countdownTimeline.setOnFinished(event -> {

        });

        countdownTimeline.play();
    }

    @FXML
    private void onMousePressed(MouseEvent event) {
        startGrowingStick();
    }

    @FXML
    private void onMouseReleased(MouseEvent event) {
        stopGrowingStick();
    }

    private void startGrowingStick() {

        // For simplicity, I'll use a Timeline here

        growTimeline = new Timeline(
                new KeyFrame(Duration.millis(10), e -> stick.increaseHeight())
        );
        growTimeline.setCycleCount(Timeline.INDEFINITE);
        growTimeline.play();
    }
    private void checkStickPosition() {
        double stickEndX = stick.getX() + stick.getHeight()+stick.getWidth();
        double rectangleStartX = newRectangle.getX();
        double rectangleEndX = newRectangle.getX() + newRectangle.getWidth();

        if ((stickEndX >= rectangleStartX && stickEndX <= rectangleEndX) || revive>0) {
            if((stickEndX >= rectangleStartX && stickEndX <= rectangleEndX)==false){
                revive--;
                score--;
            }
            mediaPlayer.play();
            // Stick is within the bounds of the newRectangle
            increaseScore();

            // Animation to move the wizard character to stickEndX gradually
            double initialX = characterImageView.getTranslateX();
            double targetX = stickEndX;

            KeyValue keyValue = new KeyValue(characterImageView.translateXProperty(), targetX);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);

            Timeline moveWizardTimeline = new Timeline(keyFrame);

            // After the animation completes, reset the wizard's position and restart the game
            moveWizardTimeline.setOnFinished(event -> {
                mediaPlayer.play();

                characterImageView.setTranslateX(initialX);
                // Reset to the initial position

                startAfterDelay();

            });

            moveWizardTimeline.play();

        } else {
            mediaPlayer2.play();

            showGameOverScreen();

        }
    }

    private void startAfterDelay() {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.1)); // Delay

        // After the pause, call start() to continue the game
        pause.setOnFinished(event -> start());

        pause.play();
    }
    private void increaseScore() {
        score++;
        if (score%7==0){
            revive++;
        }
        if (scoreText != null) {
            scoreText.setText("Score: " + score);
            scoreText.setX(150); // Set X
        }
    }
    private void showGameOverScreen() {

        // Create a Text node to display the "Game Over" message
        Text gameOverText = new Text("Game Over\nScore: " + score);
        gameOverText.setFont(Font.font(30));
        gameOverText.setFill(Color.WHITE);
        gameOverText.setX(150); // Adjust this value as needed
        gameOverText.setY(200); // Adjust this value as needed
        double x=readCherryFile();
        double t= score;
        double y= ceil(t/3);
        int f= (int) (x+y);
        writeCherryFile(f);
        // Add the Text node to the GameAnchor
        GameAnchor.getChildren().add(gameOverText);

        // Use PauseTransition to display the "Game Over" screen for 1 second
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            // Remove the "Game Over" message
            GameAnchor.getChildren().remove(gameOverText);

            // Switch back to start.fxml
            try {
                int previousRecord = loadRecord();

                // Assume currentScore is the variable holding the current score
                int currentScore =score;

                // Compare and update if necessary
                if (currentScore > previousRecord) {
                    // Update the record
                    updateRecord(currentScore);

                    // Change scene
                    SceneFactory scene = new SceneFactory();
                    Switcher switch1 = scene.createSwitcher("Switch");
                    switch1.Switch(GameAnchor, "start.fxml");
                }


                SceneFactory scene=new SceneFactory();
                Switcher switch1=scene.createSwitcher("Switch");
                switch1.Switch(GameAnchor,"start.fxml");
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        });

        pause.play();
    }
    private int readCherryFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("cherry.txt"))) {
            String line = reader.readLine();
            return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return 0; // Default value if reading fails
        }
    }

    private void writeCherryFile(int newValue) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cherry.txt"))) {
            writer.write(Integer.toString(newValue));
        } catch (IOException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }
    @FXML
    private void createSaveButton() {
        Button saveButton = new Button("Save");
        saveButton.setLayoutX(180); // X-coordinate
        saveButton.setLayoutY(250); // Y-coordinate
        saveButton.setOnAction(event -> {
            // Call a method to save the current score to a file
            saveScoreToFile();

            // Switch back to start.fxml
            try {
                mediaPlayer2.play();
                SceneFactory scene = new SceneFactory();
                Switcher switcher = scene.createSwitcher("Switch");
                switcher.Switch(GameAnchor, "start.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        // Add the button to the GameAnchor
        GameAnchor.getChildren().add(saveButton);
    }

    private void saveScoreToFile() {
        try {
            int currentScore = score; // Assuming 'score' is your current score variable
            FileWriter writer = new FileWriter("score.txt");
            writer.write(String.valueOf(currentScore));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void stopGrowingStick() {
        if (growTimeline != null) {
            growTimeline.stop();

            // Adjust the pivot point to be the starting point of the stick
            double pivotX = stick.getX() + stick.getWidth();
            double pivotY = stick.getY();

            Rotate rotate = new Rotate(0, pivotX, 330);
            stick.getTransforms().add(rotate);

            Timeline rotateTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), 90))
            );

            rotateTimeline.setOnFinished(event -> checkStickPosition()); // Check stick position after rotation is complete

            rotateTimeline.play();
        }
    }

    private GameRectangle createRandomRectangle() {
        Random random = new Random();
        double width = random.nextDouble() * 130 + 30;  // Random width between 30 and 130

        // Fixed x-coordinate and height
        double x = 279 - (width / 2);
        return new GameRectangle(x, width);
    }
    private int loadRecord() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("record.txt"))) {
            String line = reader.readLine();
            return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {
            // Handle exceptions appropriately
            throw e;
        }
    }

    // Update the record in the file
    private void updateRecord(int newRecord) throws IOException {
        try (FileWriter writer = new FileWriter("record.txt")) {
            writer.write(Integer.toString(newRecord));
        } catch (IOException e) {
            // Handle exceptions appropriately
            throw e;
        }
    }
}
