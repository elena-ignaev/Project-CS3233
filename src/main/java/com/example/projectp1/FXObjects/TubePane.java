package com.example.projectp1.FXObjects;

import com.example.projectp1.Model.TestTube;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

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
    private Circle bottom = new Circle(25);
    Arc arc = new Arc(25, 140, 25, 25, 215, 110);
//    Arc arc = new Arc(getDiameter()/2, getEmptyHeight()+getLayerHeight()+getLayerHeight(), getDiameter()/2, getDiameter()/2, 215, 110);
    private boolean shakeOnce;
    private boolean shakeTwice;
    private Line bottomLine = new Line();
    private Line topLine = new Line();
    private Line leftLine = new Line();
    private Line rightLine = new Line();
    private Arc arcOutline = new Arc(25, 140, 25, 25, 180, 180);
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

    public boolean isShakeOnce() {
        return shakeOnce;
    }

    public boolean isShakeTwice() {
        return shakeTwice;
    }

    public Arc getArc() {
        return arc;
    }

    public Circle getBottom() {
        return bottom;
    }

    public Line getBottomLine() {
        return bottomLine;
    }

    public Line getTopLine() {
        return topLine;
    }

    public Line getLeftLine() {
        return leftLine;
    }

    public Line getRightLine() {
        return rightLine;
    }

    public Arc getArcOutline() {
        return arcOutline;
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
    public void setEmpty(Rectangle empty) {
        this.empty = empty;
    }

    public void setShakeOnce(boolean shakeOnce) {
        this.shakeOnce = shakeOnce;
    }

    public void setShakeTwice(boolean shakeTwice) {
        this.shakeTwice = shakeTwice;
    }

    public void setArc(Arc arc) {
        this.arc = arc;
    }

    public void setBottom(Circle bottom) {
        this.bottom = bottom;
    }

    public void setBottomLine(Line bottomLine) {
        this.bottomLine = bottomLine;
    }

    public void setTopLine(Line topLine) {
        this.topLine = topLine;
    }

    public void setLeftLine(Line leftLine) {
        this.leftLine = leftLine;
    }

    public void setRightLine(Line rightLine) {
        this.rightLine = rightLine;
    }

    public void setArcOutline(Arc arcOutline) {
        this.arcOutline = arcOutline;
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
        getEmpty().setWidth(getDiameter());
        getEmpty().setHeight(getEmptyHeight());
        getEmpty().setFill(Color.LIGHTGREY); //empty part never change color
        getEmpty().setX(0);
        getEmpty().setY(0);

        getTopLine().setStartX(0);
        getTopLine().setLayoutX(0);
        getTopLine().setLayoutY(0);
        getTopLine().setEndX(getDiameter());
        getTopLine().setStartY(0);
        getTopLine().setEndY(0);
        getTopLine().setFill(Color.BLACK);

        //First layer of tube
        getLayer1().setWidth(getDiameter());
        getLayer1().setHeight(getLayerHeight());
        getLayer1().setFill(Color.web(getColor1())); //layer for appearance of gas
        getLayer1().setOpacity(getTransparency1());
        getLayer1().setY(this.getEmptyHeight());

        //Second layer of tube
        getLayer2().setWidth(getDiameter());
        getLayer2().setHeight(getLayerHeight());
        getLayer2().setFill(Color.web(getColor2())); //layer for solution
        getLayer2().setOpacity(getTransparency2());
        getLayer2().setY(getLayerHeight() + getEmptyHeight());

        //Third layer of tube
        getBottom().setRadius(getDiameter()/2);
        getBottom().setCenterX(getDiameter()/2);
        getBottom().setCenterY(getLayerHeight()*2 + getEmptyHeight());
        getBottom().setFill(Color.web(getColor3())); //layer for formation of precipitate
        getBottom().setOpacity(getTransparency3());
        getBottom().toBack();
        getBottom().setStroke(Color.BLACK);
        getBottom().setStrokeWidth(0.5);

        getLeftLine().setStartX(0);
        getLeftLine().setStartY(0);
        getLeftLine().setEndX(0);
        getLeftLine().setEndY(getEmptyHeight()+getLayerHeight()+getLayerHeight());

        getRightLine().setStartX(getDiameter());
        getRightLine().setStartY(0);
        getRightLine().setEndX(getDiameter());
        getRightLine().setEndY(getEmptyHeight()+getLayerHeight()+getLayerHeight());

        getArc().setType(ArcType.OPEN);
        getArc().setFill(Color.web(getColor3()));
        getArc().setOpacity(0.5);

        getArcOutline().setType(ArcType.OPEN);
        getArcOutline().setStroke(Color.BLACK);
        getArcOutline().setFill(Color.TRANSPARENT);
        getArcOutline().setStrokeWidth(0.5);
        getArcOutline().setOpacity(1);



        setPrefSize(this.getDiameter(), this.getEmptyHeight() + this.getLayerHeight()*3);

        getChildren().clear();
        getChildren().addAll( getTopLine(), getLeftLine(), getRightLine(), getBottom(), getEmpty(), getLayer1(), getLayer2(), getArc(), getArcOutline());
        this.setOpacity(0.75);
        getTopLine().setOpacity(1);
        getBottomLine().setOpacity(1);
        getLeftLine().setOpacity(1);
        getRightLine().setOpacity(1);
        getArcOutline().setStrokeWidth(0.5);
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
