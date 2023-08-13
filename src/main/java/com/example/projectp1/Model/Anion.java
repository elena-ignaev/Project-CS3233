package com.example.projectp1.Model;

public class Anion extends Substance {
    /**
     * Method either take in direct argument or index of it in the database
     * Throwing an exception when index out of bound is taken in when constructing or changing the identity of the anion
     * Throwing an exception when charge is non-negative
     * toString prints out all information about the ions
     */
    private String element;
    private int charge; //if > 0 make it the negative, if < 0 take that value
    private String reagents;
    private String reactions;
    public Anion(String element, int charge, String reagents, String reactions) {
        super(element + Math.abs(charge) + "-");
        this.element = element;
        this.charge = charge;
        this.reagents = reagents;
        this.reactions = reactions;
    }
    public Anion(int location, Database database) {
        super(database.getAnionElements().get(location)
                + database.getAnionCharge().get(location));
        this.element = database.getAnionElements().get(location);
        this.charge = database.getAnionCharge().get(location);
        this.reagents = database.getAnionReagents().get(location);
        this.reactions = database.getAnionReactions().get(location);
    }
    public String getElement() {
        return this.element;
    }
    public int getCharge() {
        return this.charge;
    }
    public String getReagents() {
        return this.reagents;
    }
    public String getReactions() {
        return this.reactions;
    }
    public void setCharge(int charge) {
        if (!validCharge(charge)) {
            throw new IllegalArgumentException("Charge of anion cannot be non-negative");
        }
        this.charge = charge;
    }
    public void setElement(String element) {
        this.element = element;
    }
    public void setReagents(String reagents) {
        this.reagents = reagents;
    }
    public void setReactions(String reactions) {
        this.reactions = reactions;
    }

    public void setAnion(int index, Database database) {
        this.element = database.getAnionElements().get(index);
        this.charge = database.getAnionCharge().get(index);
        this.reagents = database.getAnionReagents().get(index);
        this.reactions = database.getAnionReactions().get(index);
    }
    public static boolean validCharge(int charge) {
        return charge < 0;
    }
    @Override
    public void reacts() {}

    @Override
    public String toString() {
        return "Anion " + this.getName() + " properties: "
                + "\nElement: " + this.getName().substring(0,this.getName().length()-2)
                + "\nCharge: " + (-Math.abs(this.charge))
                + "\nReagents used to identify: " + this.getReagents()
                + "\nObservations when reactions occur to identify: " + this.getReactions()
                + "\n";
    }

}
