package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.Lighter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class LighterPane extends Pane implements Paintable {
    private Lighter lighter;
    public LighterPane() {
        paint();
    }
    public LighterPane(Lighter lighter) {
        this.lighter =lighter;
        paint();
    }

    public Lighter getLighter() {
        return lighter;
    }

    public void setLighter(Lighter lighter) {
        this.lighter = lighter;
        paint();
    }

    @Override
    public void paint() {
        //Head of the lighter where fire comes out
        Rectangle head = new Rectangle(160, 10);
        head.setFill(Color.DARKGRAY);

        //Body of the lighter
        Rectangle body = new Rectangle(100, 40);
        body.setX(60);
        body.setArcHeight(15.0d);
        body.setArcWidth(15.0d);
        Rectangle space = new Rectangle(40,24);
        space.setX(66);
        space.setY(10);
        Shape actualBody = Shape.subtract(body, space);
        actualBody.setFill(Color.RED);

        //Button to click to activate the lighter
        Rectangle button = new Rectangle(10,15);
        button.setX(96);
        button.setY(10);
        button.setFill(Color.BLACK);

        //fire
        Circle fire = new Circle(5);
        fire.setFill(Color.ORANGERED);
        fire.setOpacity(0);
        if (this.lighter != null){
            if (lighter.isOn()) {
                fire.setOpacity(0.5);
            } else {
                fire.setOpacity(0);
            }
        }

        setPrefSize(160,40);

        getChildren().clear();
        getChildren().addAll(head, actualBody, button,fire);
    }
}
