package com.example.projectp1.Controller;

import com.example.projectp1.FXObjects.*;
import com.example.projectp1.Model.*;
import com.example.projectp1.TestDatabase;
import javafx.application.Platform;
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
    Database database;
    public Database getDatabase() {
        return this.database;
    }
    History history;
    public History getHistory() {
        return this.history;
    }
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

    @FXML
    private ComboBox<String> splint;
    ObservableList<String> splintTypes = FXCollections.observableArrayList(
            "New splint", "Lighted splint", "Glowing splint"
    );

        //Equipment methods
    public void addEquipment(ActionEvent event) {
        if (event.getSource() == testTube) {
            // add the tube to the main pane
            TestTube tubeModel = new TestTube();
            getHistory().getTubes().add(tubeModel);
            TubePane tube = new TubePane(tubeModel);
            getExperimentSpace().getChildren().add(tube);
            setHasTestTube(true);
            // drag the tube
            tube.setOnMouseDragged(e -> Platform.runLater(() -> {
                tube.setLayoutX(e.getSceneX());
                tube.setLayoutY(e.getSceneY());
            }));
        }

        else if (event.getSource() == heatingEquipment) {
            Lighter lighterModel = new Lighter();
            getHistory().setLighter(lighterModel);
            LighterPane lighter = new LighterPane(lighterModel);
            lighter.setOnMouseDragged(e -> Platform.runLater(() -> {
                lighter.setLayoutX(e.getSceneX());
                lighter.setLayoutY(e.getSceneY());
            }));

            BunsenBurner bunsenBurner = new BunsenBurner();
            getHistory().setBunsenBurner(bunsenBurner);
            BurnerPane burner = new BurnerPane(bunsenBurner);
            getExperimentSpace().getChildren().addAll(lighter, burner);
            burner.setLayoutY(100);
            burner.setOnMouseDragged(e -> Platform.runLater(() -> {
                burner.setLayoutX(e.getSceneX());
                burner.setLayoutY(e.getSceneY());
            }));
        }

        else if (event.getSource() == testPH) {
            if (testPH.getValue().equals("Red litmus")) {
                RedLitmus redModel = new RedLitmus();
                RedLitmusPane red = new RedLitmusPane(redModel);
                getHistory().getRedLitmus().add(redModel);
                getExperimentSpace().getChildren().add(red);
                red.setOnMouseDragged(e -> Platform.runLater(() -> {
                    red.setLayoutX(e.getSceneX());
                    red.setLayoutY(e.getSceneY());
                }));
            }
            else if (testPH.getValue().equals("Blue litmus")) {
                BlueLitmus blueModel = new BlueLitmus();
                BlueLitmusPane blue = new BlueLitmusPane(blueModel);
                getHistory().getBlueLitmus().add(blueModel);
                getExperimentSpace().getChildren().add(blue);
                blue.setOnMouseDragged(e -> Platform.runLater(() -> {
                    blue.setLayoutX(e.getSceneX());
                    blue.setLayoutY(e.getSceneY());
                }));
            }
        }

        else if (event.getSource() == splint) {
            if (splint.getValue().equals("Glowing splint")) {
                Splint splintModel = new Splint("glowing");
                SplintPane splint = new SplintPane(splintModel);
                getHistory().getSplint().add(splintModel);
                getExperimentSpace().getChildren().add(splint);
                splint.setOnMouseDragged(e -> Platform.runLater(() -> {
                    splint.setLayoutX(e.getSceneX());
                    splint.setLayoutY(e.getSceneY());
                }));
            } else if (splint.getValue().equals("Lighted splint")) {
                Splint splintModel = new Splint("lighted");
                SplintPane splint = new SplintPane(splintModel);
                getHistory().getSplint().add(splintModel);
                getExperimentSpace().getChildren().add(splint);
                splint.setOnMouseDragged(e -> Platform.runLater(() -> {
                    splint.setLayoutX(e.getSceneX());
                    splint.setLayoutY(e.getSceneY());
                }));
            } else if (splint.getValue().equals("New splint")) {
                Splint splintModel = new Splint("new");
                SplintPane splint = new SplintPane(splintModel);
                getHistory().getSplint().add(splintModel);
                getExperimentSpace().getChildren().add(splint);
                splint.setOnMouseDragged(e -> Platform.runLater(() -> {
                    splint.setLayoutX(e.getSceneX());
                    splint.setLayoutY(e.getSceneY());
                }));
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
                    int cationIndex = random.nextInt(Database.getNumOfCations());
                    int anionIndex = random.nextInt(Database.getNumOfAnions());
                    Salt randomSalt = new Salt(new Cation(cationIndex, getDatabase()), new Anion(anionIndex, getDatabase()));
                    System.out.println(getExperimentSpace().getChildren().get(0));
                }
            }
            if (event.getSource() == aqueousNaOH) {

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
        splint.setItems(splintTypes);
        database = new Database("cationNames.txt", "anionNames.txt", "gas.txt");
        history = new History();
    }

}
