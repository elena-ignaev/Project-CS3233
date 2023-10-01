package com.example.projectp1.Model;


import com.example.projectp1.FXObjects.LighterPane;

import java.util.Scanner;

public class Lighter extends Tools {
    private boolean on;
    private double lastingDuration; //unit: seconds
    public Lighter() {
        super("Lighter");
        this.on = false;
        this.lastingDuration = 1.0;
    }
    public Lighter(boolean on, double lastingDuration) {
        super("Lighter");
        if (!validLastingDuration(lastingDuration)) {
            this.lastingDuration = 1.0;
            this.on = false;
        } else {
            this.on = on;
            this.lastingDuration = lastingDuration;
        }
    }

    public boolean isOn() {
        return this.on;
    }

    public double getLastingDuration() {
        return this.lastingDuration;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public void setLastingDuration(double lastingDuration) {
        if (!validLastingDuration(lastingDuration)) {
            throw new IllegalArgumentException("For safety reasons, lasting duration of lighter should not be more than 2 seconds");
        }
        this.lastingDuration = lastingDuration;
    }
    public boolean validLastingDuration(double lastingDuration) {
        return lastingDuration <= 2.0;
    }
    @Override
    public void inAction() {
        // if possible, prompt user to type in lasting duration for the lighter
        // if input is blank, go with the default timing
        System.out.print("How long will your lighter last?");
        Scanner scanner = new Scanner(System.in);
        double lastingDuration = scanner.nextDouble();

        // only allow maximum of 2.0 lasting duration because of practical safety reasons
        this.on = true;
        this.lastingDuration = lastingDuration;
    }

    @Override
    public String toString() {
        return "Lighter is on?: " + this.isOn()
                + "Lasting duration: " + this.getLastingDuration();
    }
}
