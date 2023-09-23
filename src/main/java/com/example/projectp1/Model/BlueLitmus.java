package com.example.projectp1.Model;

public class BlueLitmus extends Tools {
    private String name;
    private boolean changed; // whether or not litmus has changed form its original color
    private boolean damp;
    public BlueLitmus() {
        super("Blue litmus paper");
        this.changed = false;
        this.damp = false;
    }

    public BlueLitmus(boolean changed, boolean damp) {
        super("Blue litmus paper");
        this.changed = changed;
        this.damp = damp;}

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
        return "Blue litmus is damp? :" + this.isDamp()
                + "\nBlue litmus paper has changed color: " + this.isChanged()
                + "\n";
    }
}
