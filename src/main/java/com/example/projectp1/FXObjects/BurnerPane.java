package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.BunsenBurner;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BurnerPane extends DraggablePane implements Paintable {
    private BunsenBurner bunsenBurner;
    public BunsenBurner getBunsenBurner() {
        return bunsenBurner;
    }
    private Rectangle preBase = new Rectangle(70,30);
    private Rectangle cutBase = new Rectangle(70,30);
    private Rectangle holePlace = new Rectangle(30, 40);
    private Circle airHole = new Circle(10);
    private Rectangle secondLevel = new Rectangle(20,40);
    private Rectangle thirdLevel = new Rectangle(10,25);
    private Circle fire = new Circle(5);
    public void setBunsenBurner(BunsenBurner bunsenBurner) {
        this.bunsenBurner = bunsenBurner;
        paint();
    }

    public Rectangle getPreBase() {
        return preBase;
    }

    public void setPreBase(Rectangle preBase) {
        this.preBase = preBase;
    }

    public Rectangle getCutBase() {
        return cutBase;
    }

    public void setCutBase(Rectangle cutBase) {
        this.cutBase = cutBase;
    }

    public Rectangle getHolePlace() {
        return holePlace;
    }

    public void setHolePlace(Rectangle holePlace) {
        this.holePlace = holePlace;
    }

    public Circle getAirHole() {
        return airHole;
    }

    public void setAirHole(Circle airHole) {
        this.airHole = airHole;
    }

    public Rectangle getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(Rectangle secondLevel) {
        this.secondLevel = secondLevel;
    }

    public Rectangle getThirdLevel() {
        return thirdLevel;
    }

    public void setThirdLevel(Rectangle thirdLevel) {
        this.thirdLevel = thirdLevel;
    }

    public Circle getFire() {
        return fire;
    }

    public void setFire(Circle fire) {
        this.fire = fire;
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
        getPreBase().setArcWidth(20.0d);
        getPreBase().setArcHeight(20.0d);

        getCutBase().setY(10);

        Shape base = Shape.subtract(preBase, cutBase);
        base.setFill(Color.BLUE);
        base.setStroke(Color.BLACK);
        base.setStrokeWidth(0.5);

        getHolePlace().setX(20);
        getHolePlace().setY(-40);
        getHolePlace().setFill(Color.DARKGRAY);
        getHolePlace().setArcHeight(5.0d);
        getHolePlace().setArcWidth(5.0d);
        getHolePlace().setStroke(Color.BLACK);
        getHolePlace().setStrokeWidth(0.5);

        getAirHole().setCenterX(35);
        getAirHole().setCenterY(-15);
        getAirHole().setFill(Color.DARKGRAY);
        getAirHole().setStroke(Color.BLACK);
        getAirHole().setStrokeWidth(0.5);

        getSecondLevel().setX(25);
        getSecondLevel().setY(-80);
        getSecondLevel().setFill(Color.DARKGRAY);
        getSecondLevel().setArcWidth(5.0d);
        getSecondLevel().setArcHeight(5.0d);
        getSecondLevel().setStroke(Color.BLACK);
        getSecondLevel().setStrokeWidth(0.5);

        getThirdLevel().setX(30);
        getThirdLevel().setY(-105);
        getThirdLevel().setFill(Color.DARKGRAY);
        getThirdLevel().setArcWidth(5.0d);
        getThirdLevel().setArcHeight(5.0d);
        getThirdLevel().setStroke(Color.BLACK);
        getThirdLevel().setStrokeWidth(0.5);

        getFire().setCenterX(35);
        getFire().setCenterY(-105);
        getFire().setFill(Color.ORANGERED);
        getFire().setOpacity(0);

        if (this.getBunsenBurner() != null) {
            if (this.getBunsenBurner().getHeat()) {
                if (this.getBunsenBurner().getFire()){
                    getFire().setOpacity(1);
                    getFire().setFill(Color.ORANGERED);
                }
                getAirHole().setFill(Color.WHITESMOKE);
            } else if (this.getBunsenBurner().getAirHole()) {
                if (this.getBunsenBurner().getFire()){
                    getFire().setOpacity(0.5);
                    getFire().setFill(Color.LIGHTSKYBLUE);
                }
                getAirHole().setFill(Color.LIGHTGREY);
            } else {
                getFire().setOpacity(0);
                getAirHole().setFill(Color.DARKGRAY);
            }
        }
        getChildren().clear();
        getChildren().addAll(base,holePlace,airHole,secondLevel,thirdLevel,fire);
    }
    @Override
    public void setDraggable(Pane parent, MouseEvent e){
        Bounds bounds = parent.getLayoutBounds();
        if (bounds.getMinX() <= e.getSceneX() && e.getSceneX() <= bounds.getMaxX() - this.getPreBase().getWidth() && bounds.getMinY() <= e.getSceneY()-this.getHeight() && e.getSceneY() <= bounds.getMaxY()) {
            Platform.runLater(() -> {
                this.setLayoutX(e.getSceneX());
                this.setLayoutY(e.getSceneY());
            });
        }
    }
}
