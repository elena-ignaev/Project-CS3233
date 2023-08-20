package com.example.projectp1.Model;

public class Layer {
    private Substance content;
    private double height = 25;
    private String color = "lightgrey";
    private boolean isClear = false;
    public Layer(Substance content, String color, boolean isClear) {
        this.content = content;
        this.color = color;
        this.isClear = isClear;
    }

    public Substance getContent() {
        return this.content;
    }

    public String getColor() {
        return this.color;
    }

    public boolean isClear() {
        return this.isClear;
    }

    public void setContent(Substance content) {
        this.content = content;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setClear(boolean clear) {
        this.isClear = clear;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Layer properties: "
                + "\nContent: " + this.getContent()
                + "\nColor: " + this.getColor()
                + "\nPrecipitate?: " + !this.isClear()
                + "\n";

    }
}
