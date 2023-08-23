package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.TestTube;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TubePane extends Pane implements Paintable {
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
    public TubePane() {
        paint();
    }

    public TubePane(TestTube tubeModel) {
        if (tubeModel.getLayer1() != null && tubeModel.getLayer2() != null && tubeModel.getLayer3() != null) {
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

        //First layer of tube
        Rectangle layer1 = new Rectangle(this.getDiameter(), this.getLayerHeight());
        layer1.setFill(Color.web(getColor1())); //layer for appearance of gas
        layer1.setOpacity(getTransparency1());
        layer1.setY(this.getEmptyHeight());

        //Second layer of tube
        Rectangle layer2 = new Rectangle(this.getDiameter(), this.getLayerHeight());
        layer2.setFill(Color.web(getColor2())); //layer for solution
        layer2.setOpacity(this.getTransparency2());
        layer2.setY(this.getLayerHeight() + this.getEmptyHeight());

        //Third layer of tube
        Circle bottom = new Circle(this.getDiameter()/2);
        bottom.setCenterX(this.getDiameter()/2);
        bottom.setCenterY(this.getLayerHeight()*2 + this.getEmptyHeight());
        Shape layer3 = Shape.subtract(bottom, layer2);
        layer3.setFill(Color.web(getColor3())); //layer for formation of precipitate
        layer3.setOpacity(getTransparency3());

        setPrefSize(this.getEmptyHeight() + this.getLayerHeight()*3, this.getDiameter());


        getChildren().clear();
        getChildren().addAll(empty, layer1, layer2, layer3);
    }
}
