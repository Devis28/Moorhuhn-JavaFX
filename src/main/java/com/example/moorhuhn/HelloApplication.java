package com.example.moorhuhn;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 500);
        Image bg = new Image("bg.jpg", true);
        ImageView imgw = new ImageView(bg); //pozadie
        imgw.setFitHeight(scene.getHeight());
        imgw.setFitWidth(scene.getWidth()+300);
        imgw.setX(-150); imgw.setY(0);
        root.getChildren().add(imgw);
        Game gm = new Game(root, imgw);


        Image img = new Image("cursor.png");
        scene.setCursor(new ImageCursor(img, img.getWidth()/2, img.getHeight()/2)); // kurzor srosshair

        stage.setTitle("MoorHuhn - Dávid Teplan");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}