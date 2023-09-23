package com.example.projectp1.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Salt extends Substance{
    private Cation cation;
    private Anion anion;
    public Salt(Cation cation, Anion anion) {
        super("");
        try {
            int oxidationStateCation = 1;
            int oxidationStateAnion = 1;
            if (Character.isDigit(cation.getCharge().charAt(0))) {
                oxidationStateCation = Integer.parseInt(cation.getCharge().substring(0, 1));
            }
            if (Character.isDigit(anion.getCharge().charAt(0))) {
                oxidationStateAnion = Integer.parseInt(anion.getCharge().substring(0, 1));
            }

            String name = "";

            if (oxidationStateAnion != oxidationStateCation){
                if (oxidationStateAnion != 1) {
                    name = name.concat("(" + cation.getElement() + ")" + oxidationStateAnion);
                } else {
                    name = name.concat(cation.getElement());
                }

                if (oxidationStateCation != 1) {
                    name = name.concat("(" + anion.getElement() + ")" + oxidationStateCation);
                } else {
                    name = name.concat(anion.getElement());
                }
            } else {
                name = name.concat(cation.getElement() + anion.getElement());
            }


            this.setName(name);
            this.cation = cation;
            this.anion = anion;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("Cation or Anion is invalid");
        }
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
    public boolean validName() {
        //get reactions from its ions
        return true;
    }

    @Override
    public String toString() {
        String saltString = "Salt has invalid Cation or Anion";
        try {
            saltString =  "Salt properties: "
                    + "\nName: " + this.getName()
                    + "\nColor: " + this.getColor()
                    + "\nCation present: " + this.getCation().getName() + "\n" + this.getCation()
                    + "\nAnion present: " + this.getAnion().getName() + "\n" + this.getAnion()
                    + "\n";
        } catch (NullPointerException ex) {
            System.out.println("Cation or Anion is invalid");
        }
        return saltString;
    }
}
