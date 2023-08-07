package com.example.projectp1.Controller;

import com.example.projectp1.FXObjects.*;
import com.example.projectp1.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ExperimentSpace implements Initializable {

    // Main functionalities

    // Pane
    @FXML
    private Pane experimentSpace;

    public Pane getExperimentSpace() {
        return this.experimentSpace;
    }

    // Equipment tab
        //Equipment components
    @FXML
    private Button testTube;

    @FXML
    private Button heatingEquipment;

    @FXML
    private ComboBox<String> testPH;

    ObservableList<String> litmusPaperType = FXCollections.observableArrayList(
            "Red litmus", "Blue litmus"
    );

        //Equipment methods
    public void addEquipment(ActionEvent event) {
        if (event.getSource() == testTube) {
            // add the tube to the main pane
            TestTube tubeModel1 = new TestTube();
            TubePane tube1 = new TubePane(tubeModel1);
            getExperimentSpace().getChildren().add(tube1);

            // drag the tube
            tube1.setOnMouseDragged(e -> {
                tube1.setLayoutX(e.getX());
                tube1.setLayoutY(e.getY());
            });
        }

        else if (event.getSource() == heatingEquipment) {
            Lighter lighterModel1 = new Lighter();
            LighterPane lighter1 = new LighterPane(lighterModel1);
            getExperimentSpace().getChildren().add(lighter1);

            lighter1.setOnMouseDragged(e -> {
                lighter1.setLayoutX(e.getX());
                lighter1.setLayoutY(e.getY());
            });
        }

    }






    // Substance tab
    @FXML
    private Button aqueousNaOH;

    @FXML
    private Button aqueousNH3;

    @FXML
    private Button aqueousAgNO3;

    @FXML
    private Button aqueousBaCl2;
    @FXML
    private ComboBox<String> salts;

    ObservableList<String> saltsForQA = FXCollections.observableArrayList(
            "Random","FeSO4", "MgSO4", "ZnSO4", "CuSO4", "(NH4)2SO4", "CuCO3", "CaCO3", "FeCl3", "NaCl","Cu(NO3)2");










    // Question tab
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








    // Explanation tab
        // Explanation components
    @FXML
    private Label explanation;

    @FXML
    private Button saveQueries;
        // Explanation methods









    // Menu bar
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
