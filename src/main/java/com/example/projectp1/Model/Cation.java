package com.example.projectp1.Model;

import com.example.projectp1.Database.Database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cation extends Substance{
    private String element;
    private String charge;
    private String withNaOH;
    private String withNH3;
    private Database database;
    public Cation(String name) {
        super(name);
        if (validName()) {
            if (name.contains("(") && name.contains(")")){
                String[] tokens = name.split("[\\(\\)]");
                this.setName(tokens[0] + tokens[1] + tokens[2]);
                this.charge = tokens[2];
                this.element = tokens[1];
            } else {
                this.setName(name);
                this.charge = name.substring(name.length()-2);
                this.element = name.substring(0, name.length()-2);
            }
            this.withNaOH = "";
            this.withNH3 = "";
        }
    }
    public Cation(String element, String charge, String withNaOH, String withNH3) {
        super(element + charge + "+");
        this.element = element;
        this.charge = charge;
        this.withNaOH = withNaOH;
        this.withNH3 = withNH3;
    }
    // format <elementary symbols>(<charge>)
    public Cation(int location, Database database) {
        super("");
        try {
                this.element = database.getCationElements().get(location);
                this.charge = database.getCationCharge().get(location);
                this.setName(database.getCationElements().get(location)
                        + database.getCationCharge().get(location));
                this.withNaOH = database.getCationReactWithNaOH().get(location);
                this.withNH3 = database.getCationReactWithNH3().get(location);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Choose a number from 0-"+ (database.getCationElements().size() - 1) + " only");
        }
    }

    public String getElement() {
        return this.element;
    }
    public String getCharge() {
        return this.charge;
    }
    public String getWithNaOH() {
        return this.withNaOH;
    }
    public String getWithNH3() {
        return this.withNH3;
    }
    public void setCharge(String charge) {
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
//    public void setCation (int index, Database database) {
//        try {
//            this.element = database.getAnionElements().get(index);
//            this.charge = database.getAnionCharge().get(index);
//            this.setName(this.getElement() + this.getCharge());
//            this.withNaOH = database.getCationReactWithNaOH().get(index);
//            this.withNH3 = database.getCationReactWithNH3().get(index);
//        } catch (IndexOutOfBoundsException ex) {
//            System.out.println("Choose a number from 0-" + (database.getCationElements().size() - 1) + " only");
//        }
//    }
    @Override
    public boolean validName() {
        boolean validElement = false;
        String elementPatternString = "(Fe|Ca|Mg|Zn|NH4|Cu)";
        Pattern elementPattern = Pattern.compile(elementPatternString);
        Matcher elementMatcher = elementPattern.matcher(this.getName());
        if (elementMatcher.find()) { validElement = true;}
        boolean validCharge = false;
        String chargePatternString = "\\+|2\\+|3\\+";
        Pattern chargePattern = Pattern.compile(chargePatternString);
        Matcher chargeMatcher = chargePattern.matcher(this.getName());
        if (chargeMatcher.find()) { validCharge = true; }
        return validElement && validCharge;
    }

    @Override
    public String toString() {
        return "Cation " + this.getName() + " properties: "
                + "\nElement: " + this.getName()
                + "\nCharge: " + this.getCharge()
                + "\nWhen reacting with NaOH: " + this.getWithNaOH()
                + "\nWhen reacting with NH3: " + this.getWithNH3()
                + "\n";
    }
}
