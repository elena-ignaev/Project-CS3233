package com.example.projectp1.Model;
/*
PROPERTIES:
Click on the bunsen burner:
- 1 time: open
 */
public class BunsenBurner extends Tools {
    private boolean airHole;
    private boolean heat;
    private boolean fire;
    public BunsenBurner() {
        super("Bunsen burner");
        this.airHole = false;
        this.heat = false;
        this.fire = false;
    }

    //closed air hole, luminous flame, heating flame
    //true = inAction
    public boolean getAirHole() {
        return this.airHole;
    }
    public boolean getHeat() {
        return this.heat;
    }
    public boolean getFire() {
        return this.fire;
    }
    public void setAirHole(boolean airHole) {
        this.airHole = airHole;
    }
    public void setHeat(boolean heat) {
        this.heat = heat;
    }
    public void setFire(boolean fire) {
        this.fire = fire;
    }
    @Override
    public void inAction() { // shift position in state, if true, bunsen burner on action

    }
    @Override
    public String toString() {
        return this.getName() + " properties:"
                +"\nFire is on: " + this.getFire()
                + "\nAir hole is open: " + this.getAirHole()
                + "\nHeating flame is on: " + this.getHeat()
                + "\n";
    }
}
