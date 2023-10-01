package com.example.projectp1;

import com.example.projectp1.Database.Database;
import com.example.projectp1.Model.*;

public class TestDatabase {
    public static void main(String[] args){
        Database database = new Database("cationNames.txt","anionNames.txt", "gas.txt");
        System.out.println(database.getCationElements());
        Anion NO3 = new Anion(5, database);
        Gas So2 = new Gas(4, database);
        Cation Cu = new Cation(3, database);
        Salt salt = new Salt(Cu, NO3);
        System.out.println(Cu + "\n" + NO3 + "\n" + So2 + "\n" + salt);
    }
}
