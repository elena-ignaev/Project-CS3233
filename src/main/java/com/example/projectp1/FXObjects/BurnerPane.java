package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.BunsenBurner;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BurnerPane extends Pane implements Paintable {
    private BunsenBurner bunsenBurner;

    public BunsenBurner getBunsenBurner() {
        return bunsenBurner;
    }

    public void setBunsenBurner(BunsenBurner bunsenBurner) {
        this.bunsenBurner = bunsenBurner;
        paint();
    }

    public BurnerPane() {
        paint();
    }
    public BurnerPane(BunsenBurner bunsenBurner) {
        this.bunsenBurner = bunsenBurner;
        paint();
    }

    @Override
    public void paint() {
        //Base of bunsen burner
        Rectangle preBase = new Rectangle(70,30);
        preBase.setArcWidth(20.0d);
        preBase.setArcHeight(20.0d);

        Rectangle cutBase = new Rectangle(70,30);
        cutBase.setY(10);

        Shape base = Shape.subtract(preBase, cutBase);
        base.setFill(Color.BLUE);

        Rectangle holePlace = new Rectangle(30, 40);
        holePlace.setX(20);
        holePlace.setY(-40);
        holePlace.setFill(Color.DARKGRAY);
        holePlace.setArcHeight(5.0d);
        holePlace.setArcWidth(5.0d);

        Circle airHole = new Circle(10);
        airHole.setCenterX(35);
        airHole.setCenterY(-15);
        airHole.setFill(Color.DARKGRAY);

        Rectangle secondLevel = new Rectangle(20,40);
        secondLevel.setX(25);
        secondLevel.setY(-80);
        secondLevel.setFill(Color.DARKGRAY);
        secondLevel.setArcWidth(5.0d);
        secondLevel.setArcHeight(5.0d);

        Rectangle thirdLevel = new Rectangle(10,25);
        thirdLevel.setX(30);
        thirdLevel.setY(-105);
        thirdLevel.setFill(Color.DARKGRAY);
        thirdLevel.setArcWidth(5.0d);
        thirdLevel.setArcHeight(5.0d);

        Circle fire = new Circle(5);
        fire.setCenterX(35);
        fire.setCenterY(-105);
        fire.setFill(Color.ORANGERED);
        fire.setOpacity(0);

        if (this.getBunsenBurner() != null) {
            if (this.getBunsenBurner().getHeat()) {
                fire.setOpacity(1);
                fire.setFill(Color.ORANGERED);
                airHole.setFill(Color.WHITESMOKE);
            } else if (this.getBunsenBurner().getAirHole()) {
                fire.setOpacity(0.5);
                fire.setFill(Color.LIGHTSKYBLUE);
                airHole.setFill(Color.LIGHTGREY);
            } else {
                fire.setOpacity(0);
                airHole.setFill(Color.DARKGRAY);
            }
        }


        getChildren().clear();
        getChildren().addAll(base,holePlace,airHole,secondLevel,thirdLevel,fire);

    }
}
