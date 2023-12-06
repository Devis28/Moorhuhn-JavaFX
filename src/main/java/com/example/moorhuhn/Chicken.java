package com.example.moorhuhn;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Chicken {
    Image img = new Image("1.png");
    ImageView imgw;
    Group root;
    Timeline pohybAobr;
    int rychlost;
    int obr = 1;
    int strana;
    boolean dead = false;
    Label score;
    int size;
    static int naboje = 6;
    Game g;
    MediaPlayer mp;

    public Chicken(Group root, Label scr, ImageView imgw2, Game game) {
        this.root = root;
        score = scr;
        g = game;
        strana = (int) (Math.random()*2);

        rychlost = (int) (Math.random()*(10-6)+6);

        pohybAobr = new Timeline(new KeyFrame(Duration.millis(75), e-> {
            posun();
            zmenaObr();
        }));
        pohybAobr.setCycleCount(-1);
        pohybAobr.play();
        imgw = new ImageView(img);
        root.getChildren().add(imgw);

        if (strana==1) imgw.setLayoutX(root.getScene().getWidth());
        else {
            imgw.setLayoutX(-img.getWidth());
            imgw.setScaleX(-1);
        }
        imgw.setLayoutY(Math.random()*(root.getScene().getHeight()- img.getHeight()));

        size = (int) (Math.random()*(75-35)+35);
        imgw.setFitWidth(size);
        imgw.setFitHeight(size-5);

        String path = getClass().getResource("/shot.mp3").toString(); // zvukovy efekt po kliknuti
        Media media = new Media(path);
        mp = new MediaPlayer(media);

        imgw.setOnMouseClicked(e-> { //kliknutie na obrazok chicken (+vystel)
            if (naboje>0) {
                mp.play();
                g.vystrel();
                smrt();
            }
        });

        imgw2.setOnMouseClicked(e-> { //kliknutie na pozadie (+vystel)
            if (naboje>0) {
                mp.play();
                g.vystrel();
            }

        });
    }

    private void smrt() { //padanie sliepky, vyssi pocet bodov mensich obrazkov, skore
        Points ps = new Points(size, root);
        ps.lbl.setLayoutX(imgw.getLayoutX()+(img.getWidth())/2-20);
        ps.lbl.setLayoutY(imgw.getLayoutY()+(img.getHeight())/2+10);
        dead = true;
        if (size < 50) {
            score.setText(String.valueOf(Integer.parseInt(score.getText()) + 5));
        }
        else {
            score.setText(String.valueOf(Integer.parseInt(score.getText()) + 1));
        }
    }

    private void zmenaObr() { //animacia chicken
        switch (obr) {
            case 1 -> img = new Image("1.png");
            case 2 -> img = new Image("2.png");
            case 3 -> img = new Image("3.png");
            case 4 -> img = new Image("2.png");
            default -> img = new Image("1.png");
        }
        if (obr>=15) {
            obr = 1;
        } else {
            obr++;
        }
        imgw.setImage(img);
    }

    private void posun() { //posuvanie mrtvej chicken + rotacia
        if (dead) {
            imgw.setLayoutY(imgw.getLayoutY()+15);
            imgw.setRotate(imgw.getRotate()+30);
            if (root.getScene().getHeight() < imgw.getLayoutY()) { // ak obrazok chicken (mrtva) prejde za okraj, zmaze sa
                root.getChildren().remove(imgw);
                pohybAobr.stop();
            }
        }
        else if (imgw.getLayoutX()+ img.getWidth() < -100 || root.getScene().getWidth()+100 < imgw.getLayoutX()) {// ak obrazok zivej chicken prejde za okraj, zmaze sa + stopne sa animacia sliepky
            pohybAobr.stop();
            root.getChildren().remove(imgw);
            pohybAobr.stop();
        }
        else imgw.setLayoutX(imgw.getLayoutX() + (strana==1?-rychlost:rychlost)); //posun sliepky
    }
}
