package com.example.projectp1;

import com.example.projectp1.Model.Anion;
import com.example.projectp1.Model.Cation;
import com.example.projectp1.Model.Salt;

public class TestIonsAndSalts {
    public static void main(String args[]) {
        Cation NH4 = new Cation("(NH4)2+");
        String[] tokens = "(NH4)2+".split("[\\)\\(]");
        System.out.println(tokens[2]);
        System.out.println(NH4.getName());
        Anion SO4 = new Anion("(SO4)2-");
        System.out.println(SO4.getName());
        Salt NH42SO4 = new Salt(NH4, SO4);
        System.out.println(NH4.validName());
    }
}
