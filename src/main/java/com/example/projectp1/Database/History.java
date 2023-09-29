package com.example.projectp1.Database;

import com.example.projectp1.Model.*;

import java.io.*;
import java.util.ArrayList;

public class History {
    private ArrayList<TestTube> tubes;
    private Lighter lighter;
    private BunsenBurner bunsenBurner;
    private ArrayList<Splint> splint;
    private ArrayList<RedLitmus> redLitmus;
    private ArrayList<BlueLitmus> blueLitmus;
    private File file;

    public History() {
        tubes = new ArrayList<>(5);
        lighter = new Lighter();
        bunsenBurner = new BunsenBurner();
        splint = new ArrayList<>(2);
        redLitmus = new ArrayList<>(3);
        blueLitmus = new ArrayList<>();
        file = new File("notes.txt");
    }
    public ArrayList<TestTube> getTubes() {
        return this.tubes;
    }

    public Lighter getLighter() {
        return lighter;
    }

    public BunsenBurner getBunsenBurner() {
        return bunsenBurner;
    }

    public ArrayList<Splint> getSplint() {
        return splint;
    }

    public ArrayList<BlueLitmus> getBlueLitmus() {
        return blueLitmus;
    }

    public ArrayList<RedLitmus> getRedLitmus() {
        return redLitmus;
    }

    public void setTubes(ArrayList<TestTube> tubes) {
        this.tubes = tubes;
    }

    public void setRedLitmus(ArrayList<RedLitmus> redLitmus) {
        this.redLitmus = redLitmus;
    }

    public void setBlueLitmus(ArrayList<BlueLitmus> blueLitmus) {
        this.blueLitmus = blueLitmus;
    }

    public void setLighter(Lighter lighter) {
        this.lighter = lighter;
    }

    public void setBunsenBurner(BunsenBurner bunsenBurner) {
        this.bunsenBurner = bunsenBurner;
    }

    public void setSplint(ArrayList<Splint> splint) {
        this.splint = splint;
    }

    public void clearAll() {
        this.getTubes().clear();
        this.getSplint().clear();
        this.getRedLitmus().clear();
        this.setLighter(null);
        this.getBlueLitmus().clear();
        this.setBunsenBurner(null);
    }

    public void write(String text) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
