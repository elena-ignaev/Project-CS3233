package com.example.projectp1.Model;

import java.io.*;
import java.util.ArrayList;

public class Database {
    private ArrayList<String> cationElements = new ArrayList<>(8);
    private ArrayList<Integer> cationCharge = new ArrayList<>(8);
    private ArrayList<String> cationReactWithNaOH = new ArrayList<>(8);
    private ArrayList<String> cationReactWithNH3 = new ArrayList<>(8);
    public ArrayList<String> getCationElements() {
        return cationElements;
    }
    public ArrayList<Integer> getCationCharge() {
        return cationCharge;
    }
    public ArrayList<String> getCationReactWithNaOH() {
        return cationReactWithNaOH;
    }
    public ArrayList<String> getCationReactWithNH3() {
        return cationReactWithNH3;
    }


    private ArrayList<String> anionElements = new ArrayList<>(5);
    private ArrayList<Integer> anionCharge = new ArrayList<>(5);
    private ArrayList<String> anionReagents = new ArrayList<>(5);
    private ArrayList<String> anionReactions = new ArrayList<>(5);
    public ArrayList<String> getAnionElements() {
        return anionElements;
    }
    public ArrayList<Integer> getAnionCharge() {
        return anionCharge;
    }
    public ArrayList<String> getAnionReagents() {
        return anionReagents;
    }
    public ArrayList<String> getAnionReactions() {
        return anionReactions;
    }


    private ArrayList<String> gasName;
    private ArrayList<String> gasTest;
    private ArrayList<String> gasResult;
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
                this.getCationCharge().add(Integer.parseInt(tokenizer[1]));
                this.getCationReactWithNaOH().add(tokenizer[2]);
                this.getCationReactWithNH3().add(tokenizer[3]);

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

        } catch(IOException ex) {

        }
    }

    public void loadGasDB(String filename) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
        } catch(IOException ex) {

        }
    }

}