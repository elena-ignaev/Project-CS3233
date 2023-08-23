package com.example.projectp1.Model;

public class TestingChemicals extends Substance{
    private static String[] testingChemicals = {"NaOH", "BaCl2", "AgNO3", "NH3"};
    public TestingChemicals() {
        super("Testing chemical");
        this.setColor("lightgrey");
    }

    public TestingChemicals(String name) {
        super("Testing chemical");
        if (validName(name)) {
            this.setName(name);
            this.setColor("lightgrey");
        } else {
            System.out.println("Invalid chemical name. Please choose only from " + testingChemicals);
        }
    }

    public boolean validName(String chemicalName) {
        boolean check = false;
        for (String name:testingChemicals) {
            if (chemicalName.equals(name)) {
                check = true;
            } else {
                check = false;
            }
        }
        return check;
    }
    @Override
    public void reacts() {}
}
