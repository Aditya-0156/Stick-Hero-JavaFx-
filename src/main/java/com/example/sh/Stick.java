package com.example.sh;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//This is a SingleTon Class as constructor is Private
public class Stick extends Rectangle {
    private static final double STICK_WIDTH = 10.0;
    private static double STICK_HEIGHT = 0;

    private static Stick instance;

    private Stick(double initialX, double initialY) {
        super(initialX, initialY, STICK_WIDTH, STICK_HEIGHT);
        this.setFill(Color.BLUE);  // Set the stick color (you can change it as needed)
    }

    public static Stick getInstance(double initialX, double initialY) {

            instance = new Stick(initialX, initialY);

        return instance;
    }

    public static double getStickHeight() {
        return STICK_HEIGHT;
    }

    public static void setStickHeight(double stickHeight) {
        STICK_HEIGHT = stickHeight;
    }

    private double initialY;  // Initial Y-coordinate of the stick

    public void setInitialY(double initialY) {
        this.initialY = initialY;
    }

    // Method to increase the stick's height by one
    public void increaseHeight() {
        double currentY = getY();  // Get the current y-coordinate
        double newHeight = getHeight() + 1;  // Increase the height by one

        // Update the y-coordinate and height
        setY(currentY - 1);  // Move the stick upward by one unit
        setHeight(newHeight);
    }

    public double getInitialY() {
        return initialY;
    }
}
