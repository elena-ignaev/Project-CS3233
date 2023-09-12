package com.example.projectp1;

import com.example.projectp1.Model.Anion;
import com.example.projectp1.Model.Cation;
import com.example.projectp1.Model.Salt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestIonsAndSalts {
    public static void main(String args[]) {
        boolean validElement = false;
        String elementPatternString = "Cl|I|SO4|CO3|NO3";
        Pattern elementPattern = Pattern.compile(elementPatternString);
        Matcher elementMatcher = elementPattern.matcher("(SO4)2-");
        if (elementMatcher.find()) { validElement = true; }

        boolean validCharge = false;
        String chargePatternString = "-|2-";
        Pattern chargePattern = Pattern.compile(chargePatternString);
        Matcher chargeMatcher = chargePattern.matcher("(SO4)2-");
        if (chargeMatcher.find()) { validCharge = true; }
        System.out.println(validElement && validCharge);


        String name = "NH4+";
        if (name.contains("(") && name.contains(")")){
            String[] tokens = name.split("[()]");
            System.out.println(tokens[1] + "" + tokens[2]);
        } else {
            System.out.println(name);
        }



        Cation NH4 = new Cation("(NH4)+");
//        NH4.setName("NH4");
        System.out.println(NH4.getName());
        String[] strings = "(NH4)+".split("[)(]");
        System.out.println(strings[2]);
        System.out.println(NH4.getName());
        Anion SO4 = new Anion("(SO4)2-");
        System.out.println(SO4.getName());
        Salt NH42SO4 = new Salt(NH4, SO4);
    }
}
