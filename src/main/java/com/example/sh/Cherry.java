package com.example.sh;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Cherry extends ImageView {

    public Cherry(String ImagePath) {
        super(new Image(ImagePath));
    }

    public void createCherry(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }

    public void removeCherry(AnchorPane anchorPane) {
        anchorPane.getChildren().remove(this);
    }
}
