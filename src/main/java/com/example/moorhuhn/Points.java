package com.example.moorhuhn;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Points {
    int size;
    Group root;
    Label lbl = new Label();
    Timeline t;
    double trnsprnt = 1;

    public Points(int sz, Group rt) {
        size = sz;
        root = rt;

        if (size < 50) lbl.setText("+5");
        else lbl.setText("+1");

        t = new Timeline(new KeyFrame(Duration.millis(150), e->miznutie()));
        t.setCycleCount(6);
        t.play();

        lbl.setFont(Font.font("Arial Black", FontWeight.BOLD, 18));

        root.getChildren().add(lbl); // po zastreleni sliepky sa zobrazi +1/+5 bodov
    }

    private void miznutie() { // miznutie label-u
        lbl.setTextFill(Color.rgb(255,255,255, trnsprnt));
        trnsprnt-=0.2;
        if (trnsprnt<=0) {
            root.getChildren().remove(lbl);
        }
    }
}
