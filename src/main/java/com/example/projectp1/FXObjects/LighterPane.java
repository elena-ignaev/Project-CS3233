package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.Lighter;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class LighterPane extends DraggablePane implements Paintable {
    private Rectangle head = new Rectangle(160, 10);
    private Rectangle body = new Rectangle(100, 40);
    private Rectangle space = new Rectangle(40,24);
    private Rectangle button = new Rectangle(10,15);
    private Circle fire = new Circle(5);
    private Lighter lighter;
    public Rectangle getHead() {
        return head;
    }
    public Rectangle getBody() {
        return body;
    }
    public Rectangle getSpace() {
        return space;
    }
    public Rectangle getButton() {
        return button;
    }
    public Circle getFire() {
        return fire;
    }
    public void setHead(Rectangle head) {
        this.head = head;
        paint();
    }
    public void setBody(Rectangle body) {
        this.body = body;
        paint();
    }
    public void setSpace(Rectangle space) {
        this.space = space;
        paint();
    }
    public void setButton(Rectangle button) {
        this.button = button;
        paint();
    }
    public void setFire(Circle fire) {
        this.fire = fire;
        paint();
    }
    public LighterPane() {
        paint();
    }
    public LighterPane(Lighter lighter) {
        this.lighter = lighter;
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
        //fire
        Circle fire = new Circle(5);
        fire.setCenterY(5);
        fire.setFill(Color.ORANGERED);
        fire.setOpacity(0);
        if (this.getLighter() != null){
            if (this.getLighter().isOn()) {
                fire.setOpacity(0.5);
            } else {
                fire.setOpacity(0);
            }
        }
        //Button to click to activate the lighter
        Rectangle button = new Rectangle(10,15);
        button.setX(96);
        button.setY(10);
        FadeTransition light = new FadeTransition();
        light.setNode(fire);
        light.setAutoReverse(false);
        light.setCycleCount(1);
        light.setDuration(Duration.millis(500));
        if (this.getLighter() != null){
            if (!this.getLighter().isOn()) {
                light.setFromValue(0.5);
                light.setToValue(0);
                Platform.runLater(light::play);
            } else {
                Line line = new Line();
                line.setStartX(101);
                line.setStartY(17.5);
                line.setEndX(111);
                line.setEndY(17.5);
                PathTransition path = new PathTransition();
                path.setPath(line);
                path.setNode(button);
                path.setAutoReverse(true);
                path.setCycleCount(2);
                path.setDuration(Duration.millis(1000));
                Platform.runLater(path::play);
                light.setFromValue(0);
                light.setToValue(0.5);
                Platform.runLater(light::play);
            }
        }

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


        setPrefSize(160,40);

        getChildren().clear();
        getChildren().addAll(head, actualBody, button,fire);
    }
    @Override
    public void setDraggable(Pane parent, MouseEvent e) {
        Bounds bounds = parent.getLayoutBounds();
        if (bounds.getMinX() <= e.getSceneX() && e.getSceneX() <= bounds.getMaxX()-this.getWidth() && bounds.getMinY() <= e.getSceneY() && e.getSceneY() <= bounds.getMaxY()-this.getHeight()) {
            this.setLayoutX(e.getSceneX());
            this.setLayoutY(e.getSceneY());
        }
    }
}
