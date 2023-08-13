package com.example.projectp1;

import com.example.projectp1.Model.Cation;
import com.example.projectp1.Model.Database;

public class TestCation {
    public static void main(String[] args){
        Database database = new Database("cationNames.txt","anionNames.txt", "gas.txt");
        System.out.println(database.getCationElements());
//        Cation Cu = new Cation(4);
//        System.out.println(Cu);
    }
}
