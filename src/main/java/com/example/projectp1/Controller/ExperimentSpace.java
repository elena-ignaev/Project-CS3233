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
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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

        if (event.getSource() == heatingEquipment) {
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

        if (event.getSource() == testPH) {
            if (testPH.getValue().equals("Red litmus")) {
                RedLitmus redModel = new RedLitmus();
                RedLitmusPane red = new RedLitmusPane(redModel);
                getHistory().getRedLitmus().add(redModel);
                getExperimentSpace().getChildren().add(red);
                red.setOnMouseDragged(e -> Platform.runLater(() -> {
                    red.setLayoutX(e.getSceneX());
                    red.setLayoutY(e.getSceneY());
                }));
            } else if (testPH.getValue().equals("Blue litmus")) {
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

        if (event.getSource() == splint) {
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

    @FXML
    private ToggleGroup tube;


    ObservableList<String> saltsForQA = FXCollections.observableArrayList(
            "Random","FeSO4", "MgSO4", "ZnSO4", "CuSO4", "(NH4)2SO4", "CuCO3", "CaCO3", "FeCl3", "NaCl","Cu(NO3)2");

    public void addSubstance(ActionEvent event) {
        try {
            if (isHasTestTube()) {
                // "Random","FeSO4", "MgSO4", "ZnSO4", "CuSO4", "(NH4)2SO4", "CuCO3", "CaCO3", "FeCl3", "NaCl","Cu(NO3)2"
                ObservableList<Node> components = getExperimentSpace().getChildren();
                ArrayList<Node> tubes = new ArrayList<>();
                tubes.clear();
                for (int i = 0; i < components.size(); i++) {
                    if (components.get(i) instanceof TubePane) {
                        tubes.add(components.get(i));
                    }
                }
                if (event.getSource() == salts) {
                    if (salts.getValue().equals("Random")) {
                        Random random = new Random();
                        int cationIndex = random.nextInt(Database.getNumOfCations());
                        int anionIndex = random.nextInt(Database.getNumOfAnions());
                        Salt randomSalt = new Salt(new Cation(cationIndex, getDatabase()), new Anion(anionIndex, getDatabase()));
                        System.out.println(tubes.get(0));
                    } else if (salts.getValue().equals("FeSO4")) { // blue-green
                        ((TubePane) getExperimentSpace().getChildren().get(0)).setColor3("blue");
                    } else if (salts.getValue().equals("MgSO4")) { // white

                    } else if (salts.getValue().equals("ZnSO4")) { // white

                    } else if (salts.getValue().equals("CuSO4")) { // blue

                    } else if (salts.getValue().equals("(NH4)2SO4")) { // white

                    } else if (salts.getValue().equals("CuCO3")) { // blue

                    } else if (salts.getValue().equals("CaCO3")) { // white

                    } else if (salts.getValue().equals("FeCl3")) {

                    } else if (salts.getValue().equals("NaCl")) { // white

                    } else if (salts.getValue().equals("Cu(NO3)2")) ; // blue

                }
                if (event.getSource() == aqueousNaOH) {
                    TestingChemicals NaOH = new TestingChemicals("NaOH");
                    Layer aqueousNaOH = new Layer(NaOH, "lightgrey", false);
                    getHistory().getTubes().get(0).setLayer3(aqueousNaOH);
                    ((TubePane) tubes.get(0)).setColor3("lightgrey");
                    ((TubePane) tubes.get(0)).setTransparency3(0.5);
//                    System.out.println(tube.getSelectedToggle());
                }
                if (event.getSource() == aqueousNH3) {
                    TestingChemicals NH3 = new TestingChemicals("NH3");
                    Layer aqueousNH3 = new Layer(NH3, "lightgrey", false);
                    getHistory().getTubes().get(0).setLayer3(aqueousNH3);
                    ((TubePane) tubes.get(0)).setColor3("lightgrey");
                    ((TubePane) tubes.get(0)).setTransparency3(0.5);
                }
                if (event.getSource() == aqueousAgNO3) {
                    TestingChemicals AgNO3 = new TestingChemicals("AgNO3");
                    Layer aqueousAgNO3 = new Layer(AgNO3, "lightgrey", false);
                    getHistory().getTubes().get(0).setLayer3(aqueousAgNO3);
                    ((TubePane) tubes.get(0)).setColor3("lightgrey");
                    ((TubePane) tubes.get(0)).setTransparency3(0.5);
                }
                if (event.getSource() == aqueousBaCl2) {
                    TestingChemicals BaCl2 = new TestingChemicals("BaCl2");
                    Layer aqueousBaCl2 = new Layer(BaCl2, "lightgrey", false);
                    getHistory().getTubes().get(0).setLayer3(aqueousBaCl2);
                    ((TubePane) tubes.get(0)).setColor3("lightgrey");
                    ((TubePane) tubes.get(0)).setTransparency3(0.5);
                }
            } else {
                Alert noTestTube = new Alert(Alert.AlertType.WARNING);
                noTestTube.setTitle("Warning");
                noTestTube.setHeaderText("No test tube");
                noTestTube.setContentText("Cannot add substance when there is no test tube!");
                noTestTube.showAndWait();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            Alert noSelectedTube = new Alert(Alert.AlertType.WARNING);
            noSelectedTube.setTitle("Warning");
            noSelectedTube.setHeaderText("No select test tube");
            noSelectedTube.setContentText("Select a tube to add the substances to!");
            noSelectedTube.showAndWait();
        }

        // When adding a random salt, remember to open up a tab "Question" to test for knowledge
        // When adding a known salt, remember to open up a tab "Explanation"

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
