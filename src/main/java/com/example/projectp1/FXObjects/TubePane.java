package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.TestTube;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TubePane extends DraggablePane implements Paintable {
    /** TODO
     * Figure out how to get color from string
     * change() function, take in color and transparency, only 0.5 and 1 transparency are legal values
     */
    private TestTube tubeModel;
    private double emptyHeight = 90;
    private double layerHeight = 25;
    private double diameter = 50;
    private String color1 = "lightgrey";
    private String color2 = "lightgrey";
    private String color3 = "lightgrey";
    private double transparency1 = 1.0;
    private double transparency2 = 1.0;
    private double transparency3 = 1.0;
    private Rectangle empty = new Rectangle(50,90);
    private Rectangle layer1 = new Rectangle(50,25);
    private Rectangle layer2 = new Rectangle(50,25);
    private Shape layer3;
    private boolean shakeOnce;
    private boolean shakeTwice;
    public double getEmptyHeight() {
        return emptyHeight;
    }
    public double getDiameter() {
        return diameter;
    }
    public double getLayerHeight() {
        return layerHeight;
    }
    public String getColor1() {
        return this.color1;
    }

    public String getColor2() {
        return color2;
    }

    public String getColor3() {
        return color3;
    }

    public double getTransparency1() {
        return transparency1;
    }

    public double getTransparency2() {
        return transparency2;
    }

    public double getTransparency3() {
        return transparency3;
    }

    public TestTube getTubeModel() {
        return tubeModel;
    }

    public Rectangle getEmpty() {
        return empty;
    }

    public Rectangle getLayer1() {
        return layer1;
    }

    public Rectangle getLayer2() {
        return layer2;
    }

    public Shape getLayer3() {
        return layer3;
    }

    public boolean isShakeOnce() {
        return shakeOnce;
    }

    public boolean isShakeTwice() {
        return shakeTwice;
    }

    public void setTransparency1(double transparency1) {
        this.transparency1 = transparency1;
        paint();
    }


    public void setTransparency2(double transparency2) {
        this.transparency2 = transparency2;
        paint();
    }

    public void setTransparency3(double transparency3) {
        this.transparency3 = transparency3;
        paint();
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
        paint();
    }
    public void setEmptyHeight(double emptyHeight) {
        this.emptyHeight = emptyHeight;
        paint();
    }
    public void setLayerHeight(double layerHeight) {
        this.layerHeight = layerHeight;
        paint();
    }
    public void setColor1(String color1) {
        this.color1 = color1;
        paint();
    }
    public void setColor2(String color2) {
        this.color2 = color2;
        paint();
    }
    public void setColor3(String color3) {
        this.color3 = color3;
        paint();
    }

    public void setTubeModel(TestTube tubeModel) {
        this.tubeModel = tubeModel;
    }

    public void setLayer1(Rectangle layer1) {
        this.layer1 = layer1;
    }
    public void setLayer2(Rectangle layer2) {
        this.layer2 = layer2;
    }
    public void setLayer3(Shape layer3) {
        this.layer3 = layer3;
    }
    public void setEmpty(Rectangle empty) {
        this.empty = empty;
    }

    public void setShakeOnce(boolean shakeOnce) {
        this.shakeOnce = shakeOnce;
    }

    public void setShakeTwice(boolean shakeTwice) {
        this.shakeTwice = shakeTwice;
    }

    public TubePane() {
        paint();
    }

    public TubePane(TestTube tubeModel) {
        this.tubeModel = tubeModel;
        if (tubeModel.getLayer1() != null && tubeModel.getLayer2() != null && tubeModel.getLayer3() != null) {
            this.tubeModel = tubeModel;
            this.color1 = tubeModel.getLayer1().getColor();
            this.color2 = tubeModel.getLayer2().getColor();
            this.color3 = tubeModel.getLayer3().getColor();
            if (tubeModel.getLayer1().isClear()) {
                this.transparency1 = 0.5;
            } else {
                this.transparency1 = 1;
            }
            if (tubeModel.getLayer2().isClear()) {
                this.transparency2 = 0.5;
            } else {
                this.transparency2 = 1;
            }if (tubeModel.getLayer3().isClear()) {
                this.transparency3 = 0.5;
            } else {
                this.transparency3 = 1;
            }
        }
        paint();
    }

    @Override
    public void paint() {
        //Empty part of tube
        Rectangle empty = new Rectangle(this.getDiameter(), this.getEmptyHeight());
        empty.setFill(Color.LIGHTGREY); //empty part never change color
        empty.setX(0);
        empty.setY(0);
        setEmpty(empty);
        empty.setStroke(Color.BLACK);
        empty.setStrokeWidth(1.0);
        Line line = new Line();
        line.setStartX(0);
        line.setEndX(getDiameter());
        line.setStartY(getEmptyHeight());
        line.setEndY(getEmptyHeight());
        line.setFill(Color.LIGHTGREY);
        line.setOpacity(0.75);

        //First layer of tube
        Rectangle layer1 = new Rectangle(this.getDiameter(), this.getLayerHeight());
        layer1.setFill(Color.web(getColor1())); //layer for appearance of gas
        layer1.setOpacity(getTransparency1());
        layer1.setY(this.getEmptyHeight());
        setLayer1(layer1);
        layer1.setStroke(Color.BLACK);
        layer1.setStrokeWidth(1.0);

        //Second layer of tube
        Rectangle layer2 = new Rectangle(this.getDiameter(), this.getLayerHeight());
        layer2.setFill(Color.web(getColor2())); //layer for solution
        layer2.setOpacity(this.getTransparency2());
        layer2.setY(this.getLayerHeight() + this.getEmptyHeight());
        setLayer2(layer2);
        layer2.setStroke(Color.BLACK);
        layer2.setStrokeWidth(1.0);

        //Third layer of tube
        Circle bottom = new Circle(this.getDiameter()/2);

        bottom.setCenterX(this.getDiameter()/2);
        bottom.setCenterY(this.getLayerHeight()*2 + this.getEmptyHeight());
        bottom.setFill(Color.web(getColor3())); //layer for formation of precipitate
        bottom.setOpacity(getTransparency3());
        bottom.setStroke(Color.BLACK);
        bottom.setStrokeWidth(1.0);
        setLayer3(bottom);
        bottom.toBack();
        layer2.toFront();
        layer1.toFront();

        setPrefSize(this.getDiameter(), this.getEmptyHeight() + this.getLayerHeight()*3);

        getChildren().clear();
        getChildren().addAll(empty, layer1, bottom, layer2);
        this.setOpacity(0.75);
    }
    @Override
    public void setDraggable(Pane parent, MouseEvent e){
        Bounds bounds = parent.getLayoutBounds();
        if (bounds.getMinX() <= e.getSceneX() && e.getSceneX() <= bounds.getMaxX()-this.getDiameter() && bounds.getMinY() <= e.getSceneY() && e.getSceneY() <= bounds.getMaxY()-this.getDiameter()-this.getEmptyHeight()-this.getLayerHeight()) {
            Platform.runLater(() -> {
                this.setLayoutX(e.getSceneX());
                this.setLayoutY(e.getSceneY());
            });
        }
    }
}
