package com.example.projectp1.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ExperimentSpace implements Initializable {

    // Main functionalities
    // Substances
    @FXML
    private ComboBox<String> salts;

    ObservableList<String> saltsForQA = FXCollections.observableArrayList(
            "Random","FeSO4", "MgSO4", "ZnSO4", "CuSO4", "(NH4)2SO4", "CuCO3", "CaCO3", "FeCl3", "NaCl","Cu(NO3)2");


    // Equipment
    @FXML
    private Button testTube;


    @FXML
    private Button heatingEquipment;

    @FXML
    private ComboBox<String> testPH;

    ObservableList<String> litmusPaperType = FXCollections.observableArrayList(
            "Red litmus", "Blue litmus"
    );


    // Question and Answer
    @FXML
    private TextField cationAns;

    @FXML
    private TextField anionAns;

    @FXML
    private TextField saltAns;

    @FXML
    private Button checkAns;

    @FXML
    private Button showExplanation;

    @FXML
    private Label explanation;

    @FXML
    private Button saveQueries;


    // Menu bar functionalities
    @FXML
    private MenuItem saveProgress;

    @FXML
    private MenuItem viewNotes;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem delete;

    @FXML
    private MenuItem clearAll;

    @FXML
    private MenuItem aboutProgrammer;


    // Initialize
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        salts.setItems(saltsForQA);
        testPH.setItems(litmusPaperType);
    }

}
