package com.example.projectp1.Model;
/*
PROPERTIES:
Click on the bunsen burner:
- 1 time: open
 */
public class BunsenBurner extends Tools {
    private boolean airHole;
    private boolean heat;
    public BunsenBurner() {
        super("Bunsen burner");
        this.airHole = false;
        this.heat = false;
    }

    //closed air hole, luminous flame, heating flame
    //true = inAction
    public boolean getAirHole() {
        return this.airHole;
    }
    public boolean getHeat() {
        return this.heat;
    }
    public void setAirHole(boolean airHole) {
        this.airHole = airHole;
    }
    public void setHeat(boolean heat) {
        this.heat = heat;
    }
    @Override
    public void inAction() { // shift position in state, if true, bunsen burner on action

    }
    @Override
    public String toString() {
        return this.getName() + " properties:"
                + "\nAir hole is open: " + this.getAirHole()
                + "\nHeating flame is on: " + this.getHeat()
                + "\n";
    }
}
