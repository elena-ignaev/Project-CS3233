package com.example.projectp1.Model;

public class Cation extends Substance{
    private int charge;
    private String withNaOH;
    private String withNH3;
    // format <elementary symbols>(<charge>)
    public Cation(int location) {
        super(Database.getCationElements()[location%(Database.getCationElements().length)]
                + Database.getCationCharge()[location%(Database.getCationCharge().length)]);
        location = location%(Database.getCationElements().length);
        this.charge = Database.getCationCharge()[location];
        this.withNaOH = Database.getCationReactWithNaOH()[location];
        this.withNH3 = Database.getCationReactWithNH3()[location];
    }
    public int getCharge() {
        return this.charge;
    }
    public String getWithNaOH() {
        return this.withNaOH;
    }
    public String getWithNH3() {
        return this.withNH3;
    }
    public void setCharge(int charge) {
        if (!validCharge(charge)) {
            throw new IllegalArgumentException("Charge of cation cannot be non-positive");
        }
        this.charge = charge;
    }
    public void setWithNaOH(String withNaOH) {
        this.withNaOH = withNaOH;
    }
    public void setWithNH3(String withNH3) {
        this.withNH3 = withNH3;
    }
    public boolean validCharge(int charge) {
        return charge > 0;
    }

    @Override
    public void reacts() {
        // from database
    }

    @Override
    public String toString() {
        return "Cation " + this.getName() + " properties: "
                + "\nElement: " + this.getName()
                + "\nCharge: " + (Math.abs(this.getCharge()))
                + "\nWhen reacting with NaOH: " + this.getWithNaOH()
                + "\nWhen reacting with NH3: " + this.getWithNH3()
                + "\n";
    }
}
