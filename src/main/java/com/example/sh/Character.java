package com.example.sh;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Character {

    private ImageView characterImageView;
    private String[] walkImagePaths;  // Array to store paths of walk animation frames
    private SimpleIntegerProperty currentFrameIndex;

    public Character(ImageView characterImageView, String[] walkImagePaths) {
        this.characterImageView = characterImageView;
        this.walkImagePaths = walkImagePaths;
        this.currentFrameIndex = new SimpleIntegerProperty(0);

        // Initialize character image
        updateCharacterImage();

        // Set up walk animation
        Timeline walkAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(currentFrameIndex, 0)),
                new KeyFrame(Duration.seconds(1), e -> walkAnimation())
                // Add more KeyFrames for additional frames and durations
        );

        walkAnimation.setCycleCount(Timeline.INDEFINITE); // Repeat the animation indefinitely
        walkAnimation.play();
    }

    // Method to update character image based on the current frame index
    private void updateCharacterImage() {
        if (currentFrameIndex.get() < walkImagePaths.length) {
            String path = walkImagePaths[currentFrameIndex.get()];
            characterImageView.setImage(new Image(getClass().getResource("/" + path).toExternalForm()));
        }
    }

    // Method to handle walk animation
    private void walkAnimation() {
        currentFrameIndex.set(currentFrameIndex.get() + 1);
        if (currentFrameIndex.get() >= walkImagePaths.length) {
            currentFrameIndex.set(0);
        }
        updateCharacterImage();
    }
}
