package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.BlueLitmus;
import com.example.projectp1.Model.RedLitmus;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BlueLitmusPane extends Pane implements Paintable {
    private BlueLitmus blueLitmus;
    public BlueLitmusPane() {
        paint();
    }
    public BlueLitmusPane(BlueLitmus blueLitmus) {
        this.blueLitmus = blueLitmus;
        paint();
    }

    public BlueLitmus getBlueLitmus() {
        return blueLitmus;
    }

    public void setBlueLitmus(BlueLitmus blueLitmus) {
        this.blueLitmus = blueLitmus;
        paint();
    }


    @Override
    public void paint() {
        Rectangle paper = new Rectangle(40,80);
        paper.setFill(Color.LIGHTSKYBLUE);

        Rectangle changeable = new Rectangle(40, 80);
        changeable.setFill(Color.LIGHTSKYBLUE);
        changeable.setY(80);

        if (this.getBlueLitmus() != null){
            if (this.getBlueLitmus().isChanged()) {
                changeable.setFill(Color.MISTYROSE);
            }
        }

        setPrefSize(40,160);

        getChildren().clear();
        getChildren().addAll(paper, changeable);
    }
}
