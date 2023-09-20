package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.BlueLitmus;
import com.example.projectp1.Model.RedLitmus;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BlueLitmusPane extends DraggablePane implements Paintable {
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
    @Override
    public void setDraggable(Pane parent, MouseEvent e){
        Bounds bounds = parent.getLayoutBounds();
        if (bounds.getMinX() <= e.getSceneX() && e.getSceneX() <= bounds.getMaxX()-this.getWidth() && bounds.getMinY() <= e.getSceneY() && e.getSceneY() <= bounds.getMaxY()-this.getHeight()) {
            this.setLayoutX(e.getSceneX());
            this.setLayoutY(e.getSceneY());
        }
    }
}
