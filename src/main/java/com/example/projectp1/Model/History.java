package com.example.projectp1.Model;

import java.io.*;
import java.util.ArrayList;

public class History {
    private TestTube[] tubes;
    private Lighter lighter;
    private BunsenBurner bunsenBurner;

    public History() {
        tubes = new TestTube[5];

    }
//    private ArrayList<Cation> cations;
//    public ArrayList<Cation> getCations() {
//        return cations;
//    }
//
//
//    private ArrayList<Anion> anions;
//    public ArrayList<Anion> getAnions() {
//        return anions;
//    }
//
//
//    private ArrayList<Gas> gases;
//    public ArrayList<Gas> getGases() {
//        return gases;
//    }
//    public History(String cationFile, String anionFile, String gasFile) {
//        loadAnionDB(anionFile);
//        loadCationDB(cationFile);
//        loadGasDB(gasFile);
//    }
//    public void loadCationDB(String filename) {
//        try {
//            this.getCations().clear();
//            BufferedReader bf = new BufferedReader(new FileReader(filename));
//            // format: name, charge, withNaOH, withNH3
//            String line = bf.readLine();
//            while ((line = bf.readLine()) != null) {
//                String[] tokenizer = line.split(",");
//                Cation cation = new Cation(tokenizer[0], tokenizer[1], tokenizer[2], tokenizer[3]);
//                this.getCations().add(cation);
//                //element: tokenizer[0]
//                //charge: tokenizer[1]
//                //withNaOH: tokenizer[2]
//                //withNH3: tokenizer[3]
//                // if string contains insoluble --> opacity = 1
//                // if string does not contain insoluble and contain soluble --> opacity = 0.5
//            }
//        } catch (IOException ex) {
//
//        }
//    }
//
//    public void loadAnionDB(String filename) {
//        try {
//            this.getAnions().clear();
//            BufferedReader bf = new BufferedReader(new FileReader(filename));
//            String line = bf.readLine();
//            while((line = bf.readLine()) != null) {
//                String[] tokenizer = line.split(",");
//                Anion anion = new Anion(tokenizer[0], tokenizer[1], tokenizer[2], tokenizer[3]);
//                this.getAnions().add(anion);
//            }
//        } catch(IOException ex) {
//
//        }
//    }
//
//    public void loadGasDB(String filename) {
//        try {
//            this.getGases().clear();
//            BufferedReader bf = new BufferedReader(new FileReader(filename));
//            String line = bf.readLine();
//            while((line = bf.readLine()) != null) {
//                String[] tokenizer = line.split(",");
//                Gas gas = new Gas(tokenizer[0], tokenizer[1], tokenizer[2]);
//                this.getGases().add(gas);
//            }
//        } catch (IOException ex) {
//
//        }
//    }

}
