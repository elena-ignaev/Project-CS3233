package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.RedLitmus;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LitmusPane extends Pane implements Paintable {
    private RedLitmus redLitmus;
    public LitmusPane() {
        paint();
    }
    public LitmusPane(RedLitmus redLitmus) {
        this.redLitmus = redLitmus;
    }

    public RedLitmus getRedLitmus() {
        return redLitmus;
    }

    public void setRedLitmus(RedLitmus redLitmus) {
        this.redLitmus = redLitmus;
        paint();
    }


    @Override
    public void paint() {
        Rectangle paper = new Rectangle(40,80);
        paper.setFill(Color.MISTYROSE);

        Rectangle changeable = new Rectangle(40, 80);
        changeable.setFill(Color.MISTYROSE);
        changeable.setY(80);

        if (this.getRedLitmus() != null){
            if (this.getRedLitmus().isChanged()) {
                changeable.setFill(Color.LIGHTSKYBLUE);
            }
        }

        setPrefSize(40,160);

        getChildren().clear();
        getChildren().addAll(paper, changeable);
    }
}
