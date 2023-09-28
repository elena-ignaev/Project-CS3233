package com.example.projectp1.Model;

public class Layer {
    private Substance content;
    private double height = 25;
    private String color = "lightgrey";
    private boolean isClear = false;
    public Layer(Substance content, String color, boolean isClear) {
        this.content = content;
        if (content instanceof Salt) {
            // Fe(II), Ca, Mg, Zn, Fe(III), Cu
            if (((Salt) content).getCation().getName().contains("Cu")) {
                this.color="dodgerblue";
            } else if (((Salt) content).getCation().getName().contains("(Fe)2+")) {
                this.color="aquamarine";
            } else if (((Salt) content).getCation().getName().contains("Ca")) {
                this.color="ghostwhite";
            } else if (((Salt) content).getCation().getName().contains("Mg")) {
                this.color="ghostwhite";
            } else if (((Salt) content).getCation().getName().contains("Zn")) {
                this.color="ghostwhite";
            } else if (((Salt) content).getCation().getName().contains("(Fe)3+")) {
                this.color="firebrick";
            }
        } else {
            this.color = color;
        }
        this.isClear = isClear;
    }

    public Layer(Substance content, boolean isClear) {
        this.content = content;
            // Fe(II), Ca, Mg, Zn, Fe(III), Cu
        if (content instanceof Salt) {
            if (((Salt) content).getName().contains("Cu")) {
                this.color = "dodgerblue";
            } else if (((Salt) content).getName().contains("Fe") && ((Salt) content).getName().contains(")3")) {
                this.color = "firebrick";
            } else if (((Salt) content).getName().contains("Ca")) {
                this.color = "whitesmoke";
            } else if (((Salt) content).getName().contains("Mg")) {
                this.color = "whitesmoke";
            } else if (((Salt) content).getName().contains("Zn")) {
                this.color = "whitesmoke";
            } else if (((Salt) content).getName().contains("Fe")) {
                this.color = "aquamarine";
            }
        }
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
                + "\nClear?: " + !this.isClear()
                + "\n\n";

    }
}
