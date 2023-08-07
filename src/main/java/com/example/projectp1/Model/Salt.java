package com.example.projectp1.Model;

public class Salt extends Substance{
    private Cation cation;
    private Anion anion;
    public Salt(Cation cation, Anion anion) {
        super(cation.getName().substring(0,cation.getName().length()-2) + anion.getName().substring(0,anion.getName().length()-2));
        this.cation = cation;
        this.anion = anion;
    }
    public Cation getCation() {
        return this.cation;
    }
    public Anion getAnion() {
        return this.anion;
    }
    public void setCation(Cation cation) {
        this.cation = cation;
    }
    public void setAnion(Anion anion) {
        this.anion = anion;
    }

    @Override
    public void reacts() {
        //get reactions from its ions
    }

    @Override
    public String toString() {
        return "Salt properties: "
                + "\nName: " + this.getName()
                + "\nColor: " + this.getColor()
                + "\nCation present: " + this.cation.getName() + "\n" + this.cation
                + "\nAnion present: " + this.anion.getName() + "\n" + this.anion
                + "\n";

    }
}
