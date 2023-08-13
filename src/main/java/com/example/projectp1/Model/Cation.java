package com.example.projectp1.Model;

public class Cation extends Substance{
    private String element;
    private int charge;
    private String withNaOH;
    private String withNH3;
    public Cation(String element, int charge, String withNaOH, String withNH3) {
        super(element + charge + "+");
        this.element = element;
        this.charge = charge;
        this.withNaOH = withNaOH;
        this.withNH3 = withNH3;
    }
    // format <elementary symbols>(<charge>)
    public Cation(int location, Database database) {
        super(database.getCationElements().get(location%(database.getCationElements().size()))
                + database.getCationCharge().get(location%(database.getCationElements().size())));
        location = location%(database.getCationElements().size());
        this.charge = database.getCationCharge().get(location);
        this.withNaOH = database.getCationReactWithNaOH().get(location);
        this.withNH3 = database.getCationReactWithNH3().get(location);
    }
    public String getElement() {
        return this.element;
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
    public void setElement(String element) {
        this.element = element;
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
