package com.example.moorhuhn;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet {
    Image img = new Image("bullet.png");
    ImageView imgw;
    Group root;

    public Bullet(Group root) {
        imgw = new ImageView(img);
        imgw.setFitWidth(12);
        imgw.setFitHeight(50);
        this.root = root;
        root.getChildren().add(imgw);
    }


}
