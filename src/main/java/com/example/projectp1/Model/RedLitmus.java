package com.example.projectp1.Model;

import java.util.Scanner;

public class RedLitmus extends Tools {
    private boolean changed; // whether or not litmus has changed form its original color
    private boolean damp;
    public RedLitmus() {
        super("Red litmus paper");
        this.changed = false;
        this.damp = false;
    }

    public RedLitmus(boolean changed, boolean damp) {
        super("Red litmus paper");
        this.changed = changed;
        this.damp = damp;
    }

    public boolean isChanged() {
        return this.changed;
    }
    public boolean isDamp() {
        return this.damp;
    }
    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    public void setDamp(boolean damp) {
        this.damp = damp;
    }

    @Override
    public void inAction() {
        this.changed = true;
    }

    @Override
    public String toString() {
        return "Red litmus is damp? :" + this.isDamp()
                + "\nRed litmus paper has changed color: " + this.isChanged()
                + "\n";
    }
}
