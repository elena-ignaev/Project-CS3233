package com.example.projectp1.Model;

import java.io.*;

public class Database {
    private static String[] cationElements;
    private static int[] cationCharge;
    private static String[] cationReactWithNaOH;
    private static String[] cationReactWithNH3;
    public static String[] getCationElements() {
        return cationElements;
    }
    public static int[] getCationCharge() {
        return cationCharge;
    }
    public static String[] getCationReactWithNaOH() {
        return cationReactWithNaOH;
    }
    public static String[] getCationReactWithNH3() {
        return cationReactWithNH3;
    }


    private static String[] anionElements;
    private static int[] anionCharge;
    private static String[] anionReagents;
    private static String[] anionReactions;
    public static String[] getAnionElements() {
        return anionElements;
    }
    public static int[] getAnionCharge() {
        return anionCharge;
    }
    public static String[] getAnionReagents() {
        return anionReagents;
    }
    public static String[] getAnionReactions() {
        return anionReactions;
    }


    private static String[] gasName;
    private static String[] gasTest;
    private static String[] gasResult;
    public static String[] getGasName() {
        return gasName;
    }
    public static String[] getGasTest() {
        return gasTest;
    }
    public static String[] getGasResult() {
        return gasResult;
    }
    public Database(String cationFile, String anionFile, String gasFile) {
        loadAnionDB(anionFile);
        loadCationDB(cationFile);
        loadGasDB(gasFile);
    }
    public void loadCationDB(String filename) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
        } catch (IOException ex) {

        }
    }

    public void loadAnionDB(String filename) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
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