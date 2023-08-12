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
    public Anion(int location) {
        super("");
        if (!validIndex(location)) {
            throw new IllegalArgumentException("You can only create an anion with a number from 0 to " + (Database.getAnionElements().length - 1));
        }
        this.setName(Database.getAnionElements()[location]
                + Database.getAnionCharge()[location]);
        this.element = Database.getAnionElements()[location];
        this.charge = Database.getAnionCharge()[location];
        this.reagents = Database.getAnionReagents()[location];
        this.reactions = Database.getAnionReactions()[location];
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

    public void setAnion(int index) {
        if (!validIndex(index)) {
            throw new IllegalArgumentException("You can only create an anion with a number from 0 to " + (Database.getAnionElements().length - 1));
        }
        this.element = Database.getAnionElements()[index];
        this.charge = Database.getAnionCharge()[index];
        this.reagents = Database.getAnionReagents()[index];
        this.reactions = Database.getAnionReactions()[index];
    }
    public static boolean validCharge(int charge) {
        return charge < 0;
    }
    public static boolean validIndex(int index) {
        return 0 <= index && index < Database.getAnionElements().length;
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
