package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.RedLitmus;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class RedLitmusPane extends DraggablePane implements Paintable {
    private RedLitmus redLitmus;
    private Rectangle paper = new Rectangle(40,120);
    private Rectangle changeable = new Rectangle(40,40);
    public RedLitmusPane() {
        paint();
    }
    public RedLitmusPane(RedLitmus redLitmus) {
        this.redLitmus = redLitmus;
        paint();
    }

    public RedLitmus getRedLitmus() {
        return redLitmus;
    }
    public Rectangle getPaper() {
        return paper;
    }
    public Rectangle getChangeable() {
        return changeable;
    }

    public void setRedLitmus(RedLitmus redLitmus) {
        this.redLitmus = redLitmus;
        paint();
    }
    public void setPaper(Rectangle paper) {
        this.paper = paper;
    }
    public void setChangeable(Rectangle changeable) {
        this.changeable = changeable;
    }


    @Override
    public void paint() {
        getPaper().setFill(Color.INDIANRED);
//        paper.setStroke(Color.BLACK);
//        paper.setStrokeWidth(0.5);

        if (this.getRedLitmus()!=null){
            if (this.getRedLitmus().isChanged()) {
                getChangeable().setFill(Color.LIGHTSKYBLUE);
            } else {
                getChangeable().setFill(Color.INDIANRED);
            }
        }
        getChangeable().setY(120);
//        changeable.setStroke(Color.BLACK);
//        changeable.setStrokeWidth(0.5);

        Line topLine = new Line();
        topLine.setStartX(0);
        topLine.setStartY(0);
        topLine.setEndX(getPaper().getWidth());
        topLine.setEndY(0);

        Line bottomLine = new Line();
        bottomLine.setStartX(0);
        bottomLine.setStartY(getPaper().getHeight()+getChangeable().getHeight());
        bottomLine.setEndX(getPaper().getWidth());
        bottomLine.setEndY(getPaper().getHeight()+getChangeable().getHeight());

        Line rightLine = new Line();
        rightLine.setStartX(getPaper().getWidth());
        rightLine.setStartY(0);
        rightLine.setEndX(getPaper().getWidth());
        rightLine.setEndY(getPaper().getHeight()+getChangeable().getHeight());

        Line leftLine = new Line();
        leftLine.setStartX(0);
        leftLine.setStartY(0);
        leftLine.setEndX(0);
        leftLine.setEndY(getPaper().getHeight()+getChangeable().getHeight());


        setPrefSize(40,160);


        getChildren().clear();
        getChildren().addAll(topLine, bottomLine, rightLine, leftLine, getPaper(), getChangeable());
    }
    @Override
    public void setDraggable(Pane parent, MouseEvent e){
        Bounds bounds = parent.getLayoutBounds();
        if (bounds.getMinX() <= e.getSceneX() && e.getSceneX() <= bounds.getMaxX()-this.getWidth() && bounds.getMinY() <= e.getSceneY() && e.getSceneY() <= bounds.getMaxY()-this.getHeight()) {
            Platform.runLater(() -> {
                this.setLayoutX(e.getSceneX());
                this.setLayoutY(e.getSceneY());
            });
        }
    }
}
