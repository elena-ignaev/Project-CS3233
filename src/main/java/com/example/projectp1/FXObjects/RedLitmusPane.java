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

    public void setRedLitmus(RedLitmus redLitmus) {
        this.redLitmus = redLitmus;
        paint();
    }


    @Override
    public void paint() {
        Rectangle paper = new Rectangle(40,120);
        paper.setFill(Color.SALMON);
//        paper.setStroke(Color.BLACK);
//        paper.setStrokeWidth(0.5);

        Rectangle changeable = new Rectangle(40, 40);
        changeable.setFill(Color.SALMON);
        changeable.setY(120);
//        changeable.setStroke(Color.BLACK);
//        changeable.setStrokeWidth(0.5);

        Line topLine = new Line();
        topLine.setStartX(0);
        topLine.setStartY(0);
        topLine.setEndX(paper.getWidth());
        topLine.setEndY(0);

        Line bottomLine = new Line();
        bottomLine.setStartX(0);
        bottomLine.setStartY(paper.getHeight()+changeable.getHeight());
        bottomLine.setEndX(paper.getWidth());
        bottomLine.setEndY(paper.getHeight()+changeable.getHeight());

        Line rightLine = new Line();
        rightLine.setStartX(paper.getWidth());
        rightLine.setStartY(0);
        rightLine.setEndX(paper.getWidth());
        rightLine.setEndY(paper.getHeight()+changeable.getHeight());

        Line leftLine = new Line();
        leftLine.setStartX(0);
        leftLine.setStartY(0);
        leftLine.setEndX(0);
        leftLine.setEndY(paper.getHeight()+changeable.getHeight());

        if (this.getRedLitmus() != null){
            if (this.getRedLitmus().isChanged()) {
                changeable.setFill(Color.LIGHTSKYBLUE);
            }
        }

        setPrefSize(40,160);


        getChildren().clear();
        getChildren().addAll(topLine, bottomLine, rightLine, leftLine, paper, changeable);
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
