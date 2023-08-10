package com.example.projectp1.Model;

public class Gas extends Substance {
    private String test;
    private String result;
    public Gas(String name, String test, String result) {
        super(name);
        this.test = test;
        this.result = result;
    }
//     public Gas(int location) {
//        super(Database.getGasName()[location]);
//        this.test = Database.getGasTest()[location];
//        this.result = Database.getGasResult()[location];
//    }
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
    public void reacts() {

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
