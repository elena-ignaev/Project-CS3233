package com.example.projectp1.Model;

public class Splint extends Tools{
    private final String[] states = {"new", "lighted", "glowing"};
    private String state;
    private static int count = 0;
    public Splint(){
        super("Splint");
        this.state = this.states[0];
    }
    public Splint(String state) {
        super("Splint");
        if (!validState(state)) {
            this.state = this.states[0];
        } else {
            this.state = state;
        }
    }
    public String[] getAllStates() {
        return this.states;
    }
    public String getState() {
        return this.state;
    }
    public void setState(int i) {
        this.state = states[(count+i)%3];
    }
    public void setState(String state) {
        if (!validState(state)) {
            throw new IllegalArgumentException("Invalid state.");
        }
        this.state = state;
    }
    public boolean validState(String state) {
        boolean valid = false;
        for (String str : this.getAllStates()) {
            valid = str.equals(state);
            if (valid) {
                break;
            }
        }
        return valid;
    }

    @Override
    public void inAction() {
        // switch state
    }

    @Override
    public String toString() {
        return this.getName() + " is " + this.getState() + "\n";
    }
}
