package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.Splint;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SplintPane extends Pane implements Paintable {
    private Splint splint;
    private double splintThickness = 5;
    private double splintLength = 170;

    public double getSplintThickness() {
        return splintThickness;
    }

    public double getSplintLength() {
        return splintLength;
    }

    public Splint getSplint() {
        return splint;
    }

    public void setSplintThickness(double splintThickness) {
        this.splintThickness = splintThickness;
    }

    public void setSplintLength(double splintLength) {
        this.splintLength = splintLength;
    }

    public void setSplint(Splint splint) {
        this.splint = splint;
    }

    public SplintPane() {
        paint();
    }

    public SplintPane(Splint splint) {
        this.splint = splint;
        paint();
    }

    public SplintPane(Splint splint, double splintLength, double splintThickness) {
        this.splintThickness = splintThickness;
        this.splintLength = splintLength;
        this.splint = splint;
        paint();
    }

    @Override
    public void paint() {
        Rectangle rectangle = new Rectangle(this.getSplintLength(),this.getSplintThickness());
        rectangle.setArcHeight(35.0d);
        rectangle.setArcWidth(35.0d);
        rectangle.setFill(Color.BISQUE);

        Circle fire = new Circle(5);
        fire.setCenterY(this.getSplintThickness()/2);
        fire.setFill(Color.ORANGERED);
        fire.setOpacity(0);
        if (this.getSplint() != null){
            if (this.getSplint().getState().equals("new")) {
                fire.setOpacity(0);
            } else if (this.getSplint().getState().equals("lighted")) {
                fire.setOpacity(1);
            } else if (this.getSplint().getState().equals("glowing")) {
                fire.setFill(Color.ORANGE);
                fire.setOpacity(0.5);}
//            } else {
//                fire.setOpacity(0);
//            }
        }

        getChildren().clear();
        getChildren().addAll(rectangle, fire);

    }
}
