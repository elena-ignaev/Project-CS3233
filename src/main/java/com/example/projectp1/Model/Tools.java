package com.example.projectp1.Model;


public abstract class Tools {
    private String name;
    public Tools(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public abstract void inAction(); // what happens when you click on the tool
}
