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
import java.util.Random;
import java.util.ResourceBundle;

public class ExperimentSpace implements Initializable {

    // Main functionalities
    private boolean hasTestTube = false;
    public void setHasTestTube(boolean testTube) {
        this.hasTestTube = testTube;
    }
    public boolean isHasTestTube() {
        return this.hasTestTube;
    }

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
            TestTube tubeModel = new TestTube();
            TubePane tube = new TubePane(tubeModel);
            getExperimentSpace().getChildren().add(tube);
            setHasTestTube(true);
            // drag the tube
            tube.setOnMouseDragged(e -> {
                tube.setLayoutX(e.getX());
                tube.setLayoutY(e.getY());
            });
        }

        else if (event.getSource() == heatingEquipment) {
            Lighter lighterModel = new Lighter();
            LighterPane lighter = new LighterPane(lighterModel);
            BunsenBurner bunsenBurner = new BunsenBurner();
            BurnerPane burner = new BurnerPane(bunsenBurner);
            getExperimentSpace().getChildren().addAll(lighter, burner);
            burner.setLayoutY(100);

            lighter.setOnMouseDragged(e -> {
                lighter.setLayoutX(e.getX());
                lighter.setLayoutY(e.getY()); //error: flicking when dragging object, tab pane is transparent and can see through
            });
            burner.setOnMouseDragged(e -> {
                burner.setLayoutX(e.getX());
                burner.setLayoutY(e.getY() + 100); //error: flicking when dragging object, tab pane is transparent and can see through
            });
        }

        else if (event.getSource() == testPH) {
            if (testPH.getValue().equals("Red litmus")) {
                RedLitmus redModel = new RedLitmus();
                RedLitmusPane red = new RedLitmusPane(redModel);
                getExperimentSpace().getChildren().add(red);
                red.setOnMouseDragged(e -> {
                    red.setLayoutX(e.getX());
                    red.setLayoutY(e.getY()); //error: flicking when dragging object, tab pane is transparent and can see through
                });
            }
            else if (testPH.getValue().equals("Blue litmus")) {
                BlueLitmus blueModel = new BlueLitmus();
                BlueLitmusPane blue = new BlueLitmusPane(blueModel);
                getExperimentSpace().getChildren().add(blue);
                blue.setOnMouseDragged(e -> {
                    blue.setLayoutX(e.getX());
                    blue.setLayoutY(e.getY());
                });
            }
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

    public void addSubstance(ActionEvent event) {
        if (isHasTestTube()) {
            if (event.getSource() == salts) {
                if (salts.getValue().equals("Random")){
                    Random random = new Random();
                    int index = random.nextInt();
                }
            }
        } else {
            Alert noTestTube = new Alert(Alert.AlertType.WARNING);
            noTestTube.setTitle("Warning");
            noTestTube.setHeaderText("No test tube");
            noTestTube.setContentText("Cannot add substance when there is no test tube!");
            noTestTube.showAndWait();
        }
    }










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
