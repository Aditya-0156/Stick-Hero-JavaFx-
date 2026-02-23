package com.example.sh;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//OOPs concept GameRectangle class extends Rectangle
public class GameRectangle extends Rectangle {

    // Fixed y-coordinate for the rectangle
    private static final double FIXED_Y = 330.0;

    // Center point percentage for scoring
    private static final double CENTER_PERCENTAGE = 0.1;

    public GameRectangle(double x, double width) {
        super(x, FIXED_Y, width, 300); // Fixed height as an example
        setFill(Color.BLUE); // Set color as an example
    }

    public boolean isWithinCenter(double xPosition) {
        double centerX = getX() + getWidth() * CENTER_PERCENTAGE;
        return xPosition >= (centerX - 5) && xPosition <= (centerX + 5); // Adjust the range as needed
    }
}
