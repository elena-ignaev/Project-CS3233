package com.example.projectp1.Model;

public class Gas extends Substance {
    private String test;
    private String result;
    public Gas (int index, Database database) {
        super("");
        try {
            this.setName(database.getGasName().get(index));
            this.test = database.getGasTest().get(index);
            this.result = database.getGasResult().get(index);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Choose a number from 0-" + (database.getGasName().size() - 1) + " only");
        }
    }

    public Gas(String name, Database database) {
        super(name);
        boolean validGas = false;
        for (int i = 0; i < database.getGasName().size(); i++) {
            if (name.equals(database.getGasName().get(i))) {
                this.test = database.getGasTest().get(i);
                this.result = database.getGasResult().get(i);
                validGas = true;
                break;
            }
        }
        if (!validGas) {
            System.out.println("Invalid gas name");
        }

    }

    public Gas(String name, String test, String result) {
        super(name);
        this.test = test;
        this.result = result;
    }

    public String getTest() {
         return this.test;
    }
    public String getResult() {
         return this.result;
    }
    public void setTest(String test) {
         this.test = test;
    }
    public void setResult(String result) {
         this.result = result;
    }

    @Override
    public boolean validName() {
        return true;
    }
    @Override
    public String toString() {
         return "Gas " + this.getName() + " properties:"
                 + "\nName: " + this.getName()
                 + "\nTest to identify: " + this.getTest()
                 + "\nResult of test: " + this.getResult()
                 + "\n";
    }
}
