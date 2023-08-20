package com.example.projectp1.Model;

import java.io.*;
import java.util.ArrayList;

public class Database {
    private static int numOfCations = 0;
    private static int numOfAnions = 0;
    private static int numOfGases = 0;
    public static int getNumOfCations() {
        return numOfCations;
    }
    public static int getNumOfAnions() {
        return numOfAnions;
    }
    public static int getNumOfGases() {
        return numOfGases;
    }
    public static void setNumOfCations(int num) {
        numOfCations = num;
    }
    public static void setNumOfAnions(int num) {
        numOfAnions = num;
    }
    public static void setNumOfGases(int num) {
        numOfGases = num;
    }
    private ArrayList<String> cationElements = new ArrayList<>(8);
    private ArrayList<String> cationCharge = new ArrayList<>(8);
    private ArrayList<String> cationReactWithNaOH = new ArrayList<>(8);
    private ArrayList<String> cationReactWithNH3 = new ArrayList<>(8);
    public ArrayList<String> getCationElements() {
        return cationElements;
    }
    public ArrayList<String> getCationCharge() {
        return cationCharge;
    }
    public ArrayList<String> getCationReactWithNaOH() {
        return cationReactWithNaOH;
    }
    public ArrayList<String> getCationReactWithNH3() {
        return cationReactWithNH3;
    }


    private ArrayList<String> anionElements = new ArrayList<>(5);
    private ArrayList<String> anionCharge = new ArrayList<>(5);
    private ArrayList<String> anionReagents = new ArrayList<>(5);
    private ArrayList<String> anionReactions = new ArrayList<>(5);
    public ArrayList<String> getAnionElements() {
        return anionElements;
    }
    public ArrayList<String> getAnionCharge() {
        return anionCharge;
    }
    public ArrayList<String> getAnionReagents() {
        return anionReagents;
    }
    public ArrayList<String> getAnionReactions() {
        return anionReactions;
    }


    private ArrayList<String> gasName = new ArrayList<>(6);
    private ArrayList<String> gasTest = new ArrayList<>(6);
    private ArrayList<String> gasResult = new ArrayList<>(6);
    public ArrayList<String> getGasName() {
        return gasName;
    }
    public ArrayList<String> getGasTest() {
        return gasTest;
    }
    public ArrayList<String> getGasResult() {
        return gasResult;
    }
    public Database(String cationFile, String anionFile, String gasFile) {
        loadAnionDB(anionFile);
        loadCationDB(cationFile);
        loadGasDB(gasFile);
    }
    public void loadCationDB(String filename) {
        try {
            this.getCationElements().clear();
            this.getCationCharge().clear();
            this.getCationReactWithNaOH().clear();
            this.getCationReactWithNH3().clear();

            BufferedReader bf = new BufferedReader(new FileReader(filename));
            String line = bf.readLine();
            while((line = bf.readLine()) != null) {
                String[] tokenizer = line.split(",");

                this.getCationElements().add(tokenizer[0]);
                this.getCationCharge().add(tokenizer[1]);
                this.getCationReactWithNaOH().add(tokenizer[2]);
                this.getCationReactWithNH3().add(tokenizer[3]);
                setNumOfCations(getNumOfCations()+1);
            }
        } catch (IOException ex) {

        }
    }

    public void loadAnionDB(String filename) {
        try {
            this.getAnionElements().clear();
            this.getAnionCharge().clear();
            this.getAnionReactions().clear();
            this.getAnionReagents().clear();

            BufferedReader bf = new BufferedReader(new FileReader(filename));
            String line = bf.readLine();
            while ((line = bf.readLine()) != null) {
                String[] tokenizer = line.split(",");
                this.getAnionElements().add(tokenizer[0]);
                this.getAnionCharge().add(tokenizer[1]);
                this.getAnionReagents().add(tokenizer[2]);
                this.getAnionReactions().add(tokenizer[3]);
                setNumOfAnions(getNumOfAnions()+1);
            }
        } catch(IOException ex) {

        }
    }

    public void loadGasDB(String filename) {
        try {
            this.getGasName().clear();
            this.getGasTest().clear();
            this.getGasResult().clear();

            BufferedReader bf = new BufferedReader(new FileReader(filename));
            String line = bf.readLine();
            while ((line = bf.readLine()) != null) {
                String[] tokenizer = line.split(",");
                this.getGasName().add(tokenizer[0]);
                this.getGasTest().add(tokenizer[1]);
                this.getGasResult().add(tokenizer[2]);
                setNumOfGases(getNumOfGases()+1);
            }
        } catch(IOException ex) {

        }
    }

}