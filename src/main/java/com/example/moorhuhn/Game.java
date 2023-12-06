package com.example.moorhuhn;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.LinkedList;

public class Game {
    Group root;
    Timeline t;
    ImageView imgw;
    Label score = new Label("0");
    int naboje;
    LinkedList<Bullet> bullets = new LinkedList<>();
    Chicken k;
    Label konNab = new Label("Stlač SPACE pre nabitie nabojov");

    public Game(Group root, ImageView imgw) {
        this.root = root;
        this.imgw = imgw;
        t = new Timeline(new KeyFrame(Duration.millis(1000), e->noveKura()));
        t.setCycleCount(-1);
        t.play();
        noveKura();
        score.setLayoutX(10); score.setLayoutY(10);
        score.setFont(Font.font("Arial Black", FontWeight.BOLD, 18));
        score.setTextFill(Color.WHITE);
        konNab.setFont(Font.font("Arial Black", FontWeight.BOLD, 18));

        root.getChildren().add(score);

        imgw.setOnMouseClicked(e->vystrel());
        imgw.setOnMouseMoved(e->posunBg(e));
        imgw.setOnKeyPressed(e->nabi(e));
        imgw.requestFocus();

        int posX = 102; //zobrazenie nabojov na zaciatku
        for (int i=0; i<k.naboje; i++) {
            Bullet b = new Bullet(root);
            bullets.add(b);
            b.imgw.setLayoutX(root.getScene().getWidth()-posX);
            b.imgw.setLayoutY(root.getScene().getHeight()-55);
            posX-=17;
        }
    }

    private void posunBg(MouseEvent e) { //keď myška precházda po okraji okna, posuvá sa pozadie
        if (imgw.getX()<=-5 && e.getX()<50) imgw.setX(imgw.getX()+5);
        if (imgw.getX()-root.getScene().getWidth()>-1100 && e.getX()>root.getScene().getWidth()-50) imgw.setX(imgw.getX()-5);
    }

    private void noveKura() {
        Chicken k = new Chicken(root, score, imgw, this);
        naboje = k.naboje;
    }

    public void vystrel() {
        if (k.naboje!=0) {
            k.naboje-=1;
            root.getChildren().remove(bullets.getFirst().imgw);
            bullets.removeFirst();
        }
        if (k.naboje==0) { //zobrazenie textu pre nabitie
            konNab.setLayoutY(root.getScene().getHeight()-27);
            konNab.setLayoutX(root.getScene().getWidth()-332);
            konNab.setTextFill(Color.rgb(255,255,255, 0.6));
            root.getChildren().add(konNab);
        }
    }

    private void nabi(KeyEvent e) { //dobijanie nabojov
        if (e.getCode()==KeyCode.SPACE) {
            for (int i=0; i<k.naboje; i++) {
                root.getChildren().remove(bullets.getFirst().imgw);
                bullets.removeFirst();
            }
            k.naboje = 6;
            int posX = 102;
            for (int i=0; i<k.naboje; i++) {
                Bullet b = new Bullet(root);
                bullets.add(b);
                b.imgw.setLayoutX(root.getScene().getWidth()-posX);
                b.imgw.setLayoutY(root.getScene().getHeight()-55);
                posX-=17;
            }
            root.getChildren().remove(konNab); //odstraneneie textu pre nabitie
        }
    }
}
