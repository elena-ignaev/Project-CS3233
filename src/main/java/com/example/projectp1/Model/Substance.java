package com.example.projectp1.Model;

public abstract class Substance implements Component {
    private String name;
    private String color = "lightgrey";
    public Substance(String name) {
        this.name = name;
        this.color = "";
    }
    public String getName() {
        return this.name;
    }
    public String getColor() {
        return this.color;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public abstract void reacts();

    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }
}
