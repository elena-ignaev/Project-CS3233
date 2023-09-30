package com.example.projectp1.Controller;

import com.example.projectp1.Database.Database;
import com.example.projectp1.Database.History;
import com.example.projectp1.FXObjects.*;
import com.example.projectp1.Model.*;
import com.example.projectp1.TestExperimentSpace;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private boolean answered = false;

    public boolean isAnswered() {
        return answered;
    }
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
    private boolean hasHeatingEquipment = false;
    public boolean isHasHeatingEquipment() {
        return hasHeatingEquipment;
    }
    public void setHasHeatingEquipment(boolean hasHeatingEquipment) {
        this.hasHeatingEquipment = hasHeatingEquipment;
    }

    private boolean hasTestTube = false;
    public void setHasTestTube(boolean testTube) {
        this.hasTestTube = testTube;
    }
    public boolean isHasTestTube() {
        return this.hasTestTube;
    }


    private boolean addedSalt = false;

    public void setAddedSalt(boolean addedSalt) {
        this.addedSalt = addedSalt;
    }

    public boolean isAddedSalt() {
        return addedSalt;
    }

    @FXML
    private AnchorPane anchorPane;

    // Pane
    @FXML
    private Pane experimentSpace;
    public Pane getExperimentSpace() {
        return this.experimentSpace;
    }
    @FXML
    private Label added;

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

    @FXML
    private Button lightUp;

    @FXML
    private Rectangle aluminiumFoil;

    /*
    Adding equipments to experiment space
    Requirements:
    - Max 3 test tubes, max 3 litmus papers of each type, max 1 set of heating equipment, max 1 splint
    - Set equipment on click to carry out its inAction()
     */
    public void addEquipment(ActionEvent event) {
        if (event.getSource() == testTube) {
            // add the tube to the main pane
            if (testTubeAvailability()){
                TestTube tubeModel = new TestTube();
                getHistory().getTubes().add(tubeModel);
                TubePane tube = new TubePane(tubeModel);
                getExperimentSpace().getChildren().add(tube);
                setHasTestTube(true);
                System.out.print(tube.getTubeModel()!=null);
                // drag the tube
                tube.setOnMouseDragged(e -> Platform.runLater(() -> tube.setDraggable(experimentSpace,e)));
                // shake the tube
                tube.setOnMouseClicked(e -> Platform.runLater(() -> {
                    RotateTransition shake = new RotateTransition(Duration.millis(150));
                    shake.setNode(tube);
                    shake.setFromAngle(0);
                    shake.setToAngle(10);
                    shake.setCycleCount(20);
                    shake.setAutoReverse(true);
                    shake.play();
                    shake.setOnFinished(event1 -> System.out.println(tubeModel));
//                    shake.setOnFinished(event1 -> {
//                        added.setText("Precipitated!");
//                        showAdded();
//                    });
                }));
            } else {
                Alert tubeExists = new Alert(Alert.AlertType.WARNING);
                tubeExists.setTitle("Warning");
                tubeExists.setHeaderText("Cannot add test tubes");
                tubeExists.setContentText("Application only allows 3 test tubes!");
                tubeExists.showAndWait();
            }
        }

        if (event.getSource() == heatingEquipment) {
            if (!isHasHeatingEquipment()) {
                setHasHeatingEquipment(true);

                BunsenBurner bunsenBurner = new BunsenBurner();
                getHistory().setBunsenBurner(bunsenBurner);
                BurnerPane burner = new BurnerPane(bunsenBurner);

                Lighter lighterModel = new Lighter();
                getHistory().setLighter(lighterModel);
                LighterPane lighter = new LighterPane(lighterModel);
                lighter.getFire().setVisible(lighter.getLighter().isOn());
                lighter.setOnMouseDragged(e -> Platform.runLater(() -> lighter.setDraggable(experimentSpace,e)));
                lighter.setOnMouseClicked(e -> Platform.runLater(() -> {
                    lighter.getLighter().setOn(!lighter.getLighter().isOn());
                    lighter.setLighter(lighterModel);
                    if (lighter.getLighter().isOn()) {
                        bunsenBurner.setAirHole(true);
                        bunsenBurner.setHeat(false);
                        bunsenBurner.setFire(false);
                        burner.paint();
                    } else {
                        lightUp.setText("Heat up");
                        lightUp.setOnAction(e1 -> {
                            getHistory().getBunsenBurner().setFire(true);
                            getHistory().getBunsenBurner().setAirHole(true);
                            getHistory().getBunsenBurner().setHeat(true);
                            Alert hotWarning = new Alert(Alert.AlertType.INFORMATION);
                            hotWarning.setTitle("High heat!");
                            hotWarning.setContentText("Bunsen burner is lighted with open air hole");
                            hotWarning.setHeaderText("Be careful when handling bunsen burner");
                            hotWarning.showAndWait();
//                    for (Node node : getExperimentSpace().getChildren()){
//                        if (node instanceof BurnerPane) {
//                            ((BurnerPane) node).paint();
//                            Bounds bounds = getExperimentSpace().getLayoutBounds();
//                            node.setLayoutX((bounds.getMaxX()-bounds.getMinX())/2);
//                            node.setLayoutY((bounds.getMaxY()-bounds.getMinY())/2);
//                        }
//                    }
                        });
                    }
                }));

                lightUp.setVisible(true);
                lightUp.setOnAction(e -> {
                    if (!lighter.getLighter().isOn()) {
                        Alert lighterNotOn = new Alert(Alert.AlertType.WARNING);
                        lighterNotOn.setTitle("Warning");
                        lighterNotOn.setHeaderText("Lighter is not on yet");
                        lighterNotOn.setContentText("Must turn on lighter before lighting bunsen burner");
                        lighterNotOn.showAndWait();
                    } else if (lightUp.getText().contains("Light up")){
                        burner.getFire().setFill(Color.BLUE);
                        burner.getFire().setOpacity(0.75);
                    }
                });
                burner.setOnMouseClicked(event2-> System.out.println(event2.getX() + ", " + event2.getY()));
                getExperimentSpace().getChildren().addAll(lighter, burner);
                burner.setLayoutY(110);
                burner.setOnMouseDragged(e -> Platform.runLater(() -> burner.setDraggable(experimentSpace,e)));
            } else {
                Alert heatingEquipmentExists = new Alert(Alert.AlertType.WARNING);
                heatingEquipmentExists.setTitle("Warning");
                heatingEquipmentExists.setHeaderText("Cannot add heating equipment");
                heatingEquipmentExists.setContentText("Application only allows 1 set of heating equipment!");
                heatingEquipmentExists.showAndWait();
            }
        }

        if (event.getSource() == testPH) {
            if (testPH.getValue().equals("Red litmus")) {
                if (redLitmusAvailability()) {
                    RedLitmus redModel = new RedLitmus();
                    RedLitmusPane red = new RedLitmusPane(redModel);
                    getHistory().getRedLitmus().add(redModel);
                    getExperimentSpace().getChildren().add(red);
                    red.setOnMouseDragged(e -> Platform.runLater(() -> red.setDraggable(experimentSpace,e)));
                } else {
                    Alert noRedAvailable = new Alert(Alert.AlertType.WARNING);
                    noRedAvailable.setTitle("Warning");
                    noRedAvailable.setHeaderText("Cannot add red litmus paper");
                    noRedAvailable.setContentText("Experiment space only allows 3 red litmus papers!");
                    noRedAvailable.showAndWait();
                }
            } else if (testPH.getValue().equals("Blue litmus")) {
                if (blueLitmusAvailability()) {
                    BlueLitmus blueModel = new BlueLitmus();
                    BlueLitmusPane blue = new BlueLitmusPane(blueModel);
                    getHistory().getBlueLitmus().add(blueModel);
                    getExperimentSpace().getChildren().add(blue);
                    blue.setOnMouseDragged(e -> Platform.runLater(() -> blue.setDraggable(experimentSpace,e)));
                } else {
                    Alert noBlueAvailable = new Alert(Alert.AlertType.WARNING);
                    noBlueAvailable.setTitle("Warning");
                    noBlueAvailable.setHeaderText("Cannot add blue litmus paper");
                    noBlueAvailable.setContentText("Experiment space only allows 3 blue litmus papers!");
                    noBlueAvailable.showAndWait();
                }
            }
        }

        if (event.getSource() == splint) {
            if (splintAvailability()){
                if (splint.getValue().equals("Glowing splint")) {
                    Splint splintModel = new Splint("glowing");
                    SplintPane splint = new SplintPane(splintModel);
                    getHistory().getSplint().add(splintModel);
                    getExperimentSpace().getChildren().add(splint);
                    splint.setOnMouseDragged(e -> Platform.runLater(() -> splint.setDraggable(experimentSpace,e)));
                } else if (splint.getValue().equals("Lighted splint")) {
                    Splint splintModel = new Splint("lighted");
                    SplintPane splint = new SplintPane(splintModel);
                    getHistory().getSplint().add(splintModel);
                    getExperimentSpace().getChildren().add(splint);
                    splint.setOnMouseDragged(e -> Platform.runLater(() -> splint.setDraggable(experimentSpace,e)));
                } else if (splint.getValue().equals("New splint")) {
                    Splint splintModel = new Splint("new");
                    SplintPane splint = new SplintPane(splintModel);
                    getHistory().getSplint().add(splintModel);
                    getExperimentSpace().getChildren().add(splint);
                    splint.setOnMouseDragged(e -> Platform.runLater(() -> splint.setDraggable(experimentSpace,e)));
                }
            } else {
                Alert noSplintAvailable = new Alert(Alert.AlertType.WARNING);
                noSplintAvailable.setTitle("Warning");
                noSplintAvailable.setHeaderText("Cannot add splint");
                noSplintAvailable.setContentText("Experiment space only allows 3 splints!");
                noSplintAvailable.showAndWait();
            }
        }

    }


    public boolean redLitmusAvailability() {
        components = getExperimentSpace().getChildren();
        int i = 0;
        for (Node component : components) {
            if (component instanceof RedLitmusPane) {
                i++;
            }
            if (i==3) { return false; }
        }
        return true;
    }

    public boolean testTubeAvailability() {
        components = getExperimentSpace().getChildren();
        int i = 0;
        for (Node component : components) {
            if (component instanceof TubePane) {
                i++;
            }
            if (i==3) { return false; }
        }
        return true;
    }

    public boolean blueLitmusAvailability() {
        components = getExperimentSpace().getChildren();
        int i = 0;
        for (Node component : components) {
            if (component instanceof BlueLitmusPane) {
                i++;
            }
            if (i==3) { return false; }
        }
        return true;
    }

    public boolean splintAvailability() {
        components = getExperimentSpace().getChildren();
        int i = 0;
        for (Node component : components) {
            if (component instanceof SplintPane) {
                i++;
            }
            if (i==3) { return false; }
        }
        return true;
    }



    // Substance tab
    @FXML
    private Button blackPanel;
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

    @FXML
    private RadioButton tube1;

    @FXML
    private RadioButton tube2;

    @FXML
    private RadioButton tube3;
    @FXML
    private Rectangle blackPan;
    ObservableList<Node> components;
    ArrayList<Node> tubes = new ArrayList<>();

    public void showBlackPanel(ActionEvent event) {
        if (blackPan.isVisible()) {
            blackPan.setVisible(false);
            blackPanel.setText("Show black panel");
        } else {
            blackPan.setVisible(true);
            blackPan.setOnMouseDragged(event1 -> {
                Bounds bounds = getExperimentSpace().getLayoutBounds();
                if (bounds.getMinX() <= event1.getSceneX() && event1.getSceneX() <= bounds.getMaxX()-blackPan.getWidth() && bounds.getMinY() <= event1.getSceneY() && event1.getSceneY() <= bounds.getMaxY()-blackPan.getHeight()) {
                    Platform.runLater(() -> {
                        blackPan.setLayoutX(event1.getSceneX());
                        blackPan.setLayoutY(event1.getSceneY());
                    });
                }
            });
            blackPanel.setText("Hide black panel");
        }
    }

    // Updating the list of test tubes in experiment screen
    public void resetTube() {
        components = getExperimentSpace().getChildren();
        tubes.clear();
        for (Node component : components) {
            if (component instanceof TubePane) {
                tubes.add(component);
            }
        }
    }

    // return index of the test tube selected in toggle group radiobutton
    public int findTubeIndex() {
        // Using regex to find index of test tube from its radiobutton by matching a number
        int index=0;
        String text = ((RadioButton) tube.getSelectedToggle()).getText();
        String patternString = "[1-9]";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            index = Integer.parseInt(text.substring(matcher.start(), matcher.end()));
        }
        return (index - 1);
    }
    public void showAdded() {
        added.setAlignment(Pos.CENTER);
        added.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setNode(added);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setAutoReverse(false);
        fade.setCycleCount(1);
        fade.setDelay(Duration.millis(3000));
        fade.setDuration(Duration.millis(1000));
        fade.play();
        fade.setOnFinished(e -> added.setVisible(false));
    }


    /*
    Add substance to test tube
    Requirements:
    - There is at least one test tube in the experiment space
    - There is a selected test tube for adding the substances
    - The selected test tube already contains salt to be tested on
     */
    private boolean ammoniaProduced = false;
    public void addSubstance(ActionEvent event) {
        try {
            if (isHasTestTube()) {
                // "Random","FeSO4", "MgSO4", "ZnSO4", "CuSO4", "(NH4)2SO4", "CuCO3", "CaCO3", "FeCl3", "NaCl","Cu(NO3)2"
                if (event.getSource() == salts) {
                    resetTube();
                    if (salts.getValue() != null ){
                        setAddedSalt(true);
                        if (salts.getValue().equals("Random")) {
                            resetTube();
                            Random random = new Random();
                            int cationIndex = random.nextInt(getDatabase().getCationCharge().size());
                            Cation cation = new Cation(cationIndex, getDatabase());
                            int anionIndex = random.nextInt(getDatabase().getAnionCharge().size());
                            Anion anion = new Anion(anionIndex, getDatabase());
                            Salt randomSalt = new Salt(cation, anion);
                            Layer newLayer = new Layer(randomSalt, false);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(newLayer);
                            ((TubePane) tubes.get(findTubeIndex())).setColor3(newLayer.getColor());
                            System.out.println(randomSalt.getName() + ", " + newLayer.getColor());
                        } else if (salts.getValue().equals("FeSO4")) { // blue-green
                            Cation Fe = new Cation("(Fe)2+");
                            Anion SO4 = new Anion("(SO4)2-");
                            Salt FeSO4 = new Salt(Fe, SO4);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(new Layer(FeSO4,"aquamarine",false));
                            ((TubePane) tubes.get(findTubeIndex())).setColor3("aquamarine");
                        } else if (salts.getValue().equals("MgSO4")) { // white
                            Cation Mg = new Cation("(Mg)2+");
                            Anion SO4 = new Anion("(SO4)2-");
                            Salt MgSO4 = new Salt(Mg, SO4);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(new Layer(MgSO4,"ghostwhite",false));
                            ((TubePane) tubes.get(findTubeIndex())).setColor3("ghostwhite");
                        } else if (salts.getValue().equals("ZnSO4")) { // white
                            Cation Zn = new Cation("(Zn)2+");
                            Anion SO4 = new Anion("(SO4)2-");
                            Salt ZnSO4 = new Salt(Zn, SO4);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(new Layer(ZnSO4,"ghostwhite",false));
                            ((TubePane) tubes.get(findTubeIndex())).setColor3("ghostwhite");
                        } else if (salts.getValue().equals("CuSO4")) { // blue
                            Cation Cu = new Cation("(Cu)2+");
                            Anion SO4 = new Anion("(SO4)2-");
                            Salt CuSO4 = new Salt(Cu, SO4);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(new Layer(CuSO4,"dodgerblue",false));
                            ((TubePane) tubes.get(findTubeIndex())).setColor3("dodgerblue");
                        } else if (salts.getValue().equals("(NH4)2SO4")) { // white
                            Cation NH4 = new Cation("(NH4)2+");
                            Anion SO4 = new Anion("(SO4)2-");
                            Salt NH42SO4 = new Salt(NH4, SO4);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(new Layer(NH42SO4,"ghostwhite",false));
                            ((TubePane) tubes.get(findTubeIndex())).setColor3("ghostwhite");
                        } else if (salts.getValue().equals("CuCO3")) { // blue
                            Cation Cu = new Cation("(Cu)2+");
                            Anion CO3 = new Anion("(CO3)2-");
                            Salt CuCO3 = new Salt(Cu, CO3);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(new Layer(CuCO3,"mediumturquoise",false));
                            ((TubePane) tubes.get(findTubeIndex())).setColor3("mediumturquoise");
                        } else if (salts.getValue().equals("CaCO3")) { // white
                            Cation Ca = new Cation("(Ca)2+");
                            Anion CO3 = new Anion("(CO3)2-");
                            Salt CaCO3 = new Salt(Ca, CO3);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(new Layer(CaCO3,"ghostwhite",false));
                            ((TubePane) tubes.get(findTubeIndex())).setColor3("ghostwhite");
                        } else if (salts.getValue().equals("FeCl3")) { // red-brown
                            Cation Fe = new Cation("(Fe)3+");
                            Anion Cl = new Anion("(Cl)-");
                            Salt FeCl3 = new Salt(Fe, Cl);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(new Layer(FeCl3,"firebrick",false));
                            ((TubePane) tubes.get(findTubeIndex())).setColor3("firebrick");
                        } else if (salts.getValue().equals("NaCl")) { // white
                            Cation Na = new Cation("(Na)+");
                            Anion Cl = new Anion("(Cl)-");
                            Salt NaCl = new Salt(Na, Cl);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(new Layer(NaCl,"ghostwhite",false));
                            ((TubePane) tubes.get(findTubeIndex())).setColor3("ghostwhite");
                        } else if (salts.getValue().equals("Cu(NO3)2")) { // blue
                            Cation Cu = new Cation("(Cu)2+");
                            Anion NO3 = new Anion("(NO3)-");
                            Salt CuNO32 = new Salt(Cu, NO3);
                            ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer3(new Layer(CuNO32,"royalblue",false));
                            ((TubePane) tubes.get(findTubeIndex())).setColor3("royalblue");
                        }
                        ((TubePane) tubes.get(findTubeIndex())).getArc().setOpacity(0);
                    }
                }
                if (isAddedSalt() && tube.getSelectedToggle() != null) {
                    /*
                    TODO:
                    - make exception for adding NaOH to ammonium salts (releasing NH3 instead)
                     */
                    FadeTransition precipitate = new FadeTransition();
                    precipitate.setFromValue(0.5);
                    precipitate.setToValue(1);
                    precipitate.setDuration(Duration.millis(2000));
                    precipitate.setCycleCount(1);
                    precipitate.setAutoReverse(false);

                    FillTransition changeColor = new FillTransition(Duration.millis(2000));
                    changeColor.setDelay(Duration.millis(1000));
                    changeColor.setCycleCount(1);
                    changeColor.setAutoReverse(false);

                    if (event.getSource() == aqueousNaOH) {
                        Platform.runLater(() -> {
                            resetTube();
                            TestingChemicals NaOH = new TestingChemicals("NaOH");
                            Layer aqueousNaOH = new Layer(NaOH, "lightgrey", false);
//                            getHistory().getTubes().get(findTubeIndex()).setLayer3(aqueousNaOH);
                            ((TubePane) tubes.get(findTubeIndex())).setTransparency3(0.5);
                            if (((TubePane) tubes.get(findTubeIndex())).getTubeModel().getLayer3().getContent().getName().contains("NO3")){
                                aluminiumFoil.setVisible(true);
                                added.setText("You are provided with aluminium foil");
                                added.setVisible(true);
                                showAdded();
                                lightUp.setText("Add foil");
                                lightUp.setVisible(true);
                                lightUp.setOnAction(e -> Platform.runLater(() ->{
                                    aluminiumFoil.setX(tubes.get(findTubeIndex()).getLayoutX());
                                    aluminiumFoil.setY(tubes.get(findTubeIndex()).getLayoutY());
                                    aluminiumFoil.setOnMouseDragged(event1 -> {
                                        Bounds bounds = getExperimentSpace().getLayoutBounds();
                                        if (bounds.getMinX() <= event1.getSceneX() && event1.getSceneX() <= bounds.getMaxX()-aluminiumFoil.getWidth() && bounds.getMinY() <= event1.getSceneY() && event1.getSceneY() <= bounds.getMaxY()-aluminiumFoil.getHeight()) {
                                            Platform.runLater(() -> {
                                                aluminiumFoil.setLayoutX(event1.getX());
                                                aluminiumFoil.setLayoutY(event1.getY());
                                            });
                                        }
                                    });
                                }));
                            } else {
                                precipitate.setNode(((TubePane) tubes.get(findTubeIndex())).getArc());
                                precipitate.setDelay(Duration.millis(100));
                                precipitate.play();
                            }
                            if (((TubePane) tubes.get(findTubeIndex())).getTubeModel().getLayer3().getContent().getName().contains("NH4")){
                                ammoniaProduced = true;
                                Layer ammonia = new Layer(new Gas("NH3", database), true);
                                ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer2(ammonia);
                                ((TubePane) tubes.get(findTubeIndex())).getTubeModel().setLayer1(ammonia);
                            }
                        });
                    }
                    if (event.getSource() == aqueousNH3) {
                        Platform.runLater(()->{
                            resetTube();
                            TestingChemicals NH3 = new TestingChemicals("NH3");
                            Layer aqueousNH3 = new Layer(NH3, "lightgrey", false);
//                            getHistory().getTubes().get(findTubeIndex()).setLayer3(aqueousNH3);
                            ((TubePane) tubes.get(findTubeIndex())).setTransparency3(0.55);
                            precipitate.setNode(((TubePane) tubes.get(findTubeIndex())).getArc());
                            precipitate.setDelay(Duration.millis(100));
                            precipitate.play();
                        });
                    }
                    if (event.getSource() == aqueousAgNO3) {
                        Platform.runLater(() -> {
                            resetTube();
                            TestingChemicals AgNO3 = new TestingChemicals("AgNO3");
                            Layer aqueousAgNO3 = new Layer(AgNO3, "lightgrey", false);
//                            getHistory().getTubes().get(findTubeIndex()).setLayer3(aqueousAgNO3);
                            ((TubePane) tubes.get(findTubeIndex())).setTransparency3(0.5);
                            System.out.println(((TubePane) tubes.get(findTubeIndex())).getTubeModel().getLayer3().getContent().getName());
                            changeColor.setShape(((TubePane) tubes.get(findTubeIndex())).getArc());
                            changeColor.setFromValue(Color.web(((TubePane) tubes.get(findTubeIndex())).getTubeModel().getLayer3().getColor()));
                            if (((TubePane) tubes.get(findTubeIndex())).getTubeModel().getLayer3().getContent().getName().contains("Cl")){
                                changeColor.setToValue(Color.GHOSTWHITE);
                                changeColor.play();
                            }
                            if (((TubePane) tubes.get(findTubeIndex())).getTubeModel().getLayer3().getContent().getName().contains("I")) {
                                changeColor.setToValue(Color.YELLOW);
                                changeColor.play();
                                precipitate.setNode(((TubePane) tubes.get(findTubeIndex())).getArc());
                                precipitate.setDelay(Duration.millis(100));
                                precipitate.play();
                            }
                        });
                    }
                    if (event.getSource() == aqueousBaCl2) {
                        Platform.runLater(() -> {
                            resetTube();
                            TestingChemicals BaCl2 = new TestingChemicals("BaCl2");
                            Layer aqueousBaCl2 = new Layer(BaCl2, "lightgrey", false);
//                            getHistory().getTubes().get(findTubeIndex()).setLayer3(aqueousBaCl2);
                            ((TubePane) tubes.get(findTubeIndex())).setTransparency3(0.5);
                            changeColor.setShape((((TubePane) tubes.get(findTubeIndex())).getArc()));
                            changeColor.setFromValue(Color.web(((TubePane) tubes.get(findTubeIndex())).getTubeModel().getLayer3().getColor()));
                            if (((TubePane) tubes.get(findTubeIndex())).getTubeModel().getLayer3().getContent().getName().contains("SO4")){
                                changeColor.setToValue(Color.GHOSTWHITE);
                                changeColor.play();
                                precipitate.setNode(((TubePane) tubes.get(findTubeIndex())).getArc());
                                precipitate.setDelay(Duration.millis(100));
                                precipitate.play();
                            }

                        });
                    }
                    if (tube.getSelectedToggle() != null) {
                        added.setText("");
                        showAdded();
                    }
                } else if (!isAddedSalt()) {
                    Alert noSalt = new Alert(Alert.AlertType.WARNING);
                    noSalt.setTitle("Warning");
                    noSalt.setHeaderText("No salt");
                    noSalt.setContentText("Add salt before adding testing chemicals!");
                    noSalt.showAndWait();
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
            noSelectedTube.setHeaderText("No selected test tube");
            noSelectedTube.setContentText("Select a tube to add the substances to!");
            noSelectedTube.showAndWait();
            if (!noSelectedTube.isShowing()) {
                salts.getSelectionModel().clearSelection();
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            Alert notEnoughTube = new Alert(Alert.AlertType.WARNING);
            notEnoughTube.setTitle("Warning");
            notEnoughTube.setHeaderText("No such test tube");
            notEnoughTube.setContentText("Make sure the test tube is added to experiment space!");
            notEnoughTube.showAndWait();
            if (!notEnoughTube.isShowing()) {
                salts.getSelectionModel().clearSelection();
                salts.setValue(null);
            }
        }
    }

    // Question tab
    private boolean openedQueries = false;

    public boolean isOpenedQueries() {
        return openedQueries;
    }
    public void setOpenedQueries(boolean openedQueries) {
        this.openedQueries = openedQueries;
    }
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
    private ToggleGroup questionTube;
    @FXML
    private RadioButton questionTube1;
    @FXML
    private RadioButton questionTube2;
    @FXML
    private RadioButton questionTube3;
    public void viewQuickNotes(ActionEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Desktop.getDesktop().open(new File("notes.txt"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public int findTubeAnsIndex() {
        int index=0;
        String text = ((RadioButton) questionTube.getSelectedToggle()).getText();
        String patternString = "[1-9]";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            index = Integer.parseInt(text.substring(matcher.start(), matcher.end()));
        }
        return (index - 1);
    }
    public void question(ActionEvent event) {
        if (event.getSource() == checkAns) {
            try {
                setAnswered(true);
                String cation = cationAns.getText();
                String anion = anionAns.getText();
                String salt = saltAns.getText();
                Salt correctSalt = (Salt) ((TubePane) tubes.get(findTubeAnsIndex())).getTubeModel().getLayer3().getContent();
                System.out.println("Your answer: " + cation + ", " + anion + ", " + salt + "\nCorrect answer: " + correctSalt.getName());
                if (salt.equals(((TubePane) tubes.get(findTubeAnsIndex())).getTubeModel().getLayer3().getContent().getName())) {
                    Alert correctAnswer = new Alert(Alert.AlertType.CONFIRMATION);
                    correctAnswer.setTitle("Correct answer");
                    correctAnswer.setHeaderText("Good job! You got the salt");
                    correctAnswer.setContentText("Proceed to Explanation tab for reasons why");
                    correctAnswer.showAndWait();
                    if (!correctAnswer.isShowing()) {
                        String template = "The salt has been added is " + correctSalt.getName() +
                                ". The cation is " + correctSalt.getCation().getName() + " because " +
                                ((TubePane) tubes.get(findTubeAnsIndex())).getColor3() +
                                " insoluble precipitate is formed when added either NaOH or NH3.\nThe anion is " +
                                correctSalt.getAnion().getName() + " because when tested with " +
                                correctSalt.getAnion().getReagents() + " there is " + correctSalt.getAnion().getReactions() + ".";
                        explanation.setText(template);
                    }
                } else {
                    Alert wrongAnswer = new Alert(Alert.AlertType.ERROR);
                    wrongAnswer.setTitle("Wrong answer");
                    wrongAnswer.setHeaderText("Your answer is not correct. Try again!");
                    wrongAnswer.setContentText("Continue the experiment or refer to explanation");
                    wrongAnswer.showAndWait();
                    saltAns.clear();
                    anionAns.clear();
                    cationAns.clear();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                Alert noSelectedTube = new Alert(Alert.AlertType.WARNING);
                noSelectedTube.setTitle("Warning");
                noSelectedTube.setHeaderText("No selected test tube");
                noSelectedTube.setContentText("Select or add a tube!");
                noSelectedTube.showAndWait();
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                Alert noSuchTube = new Alert(Alert.AlertType.WARNING);
                noSuchTube.setTitle("Warning");
                noSuchTube.setHeaderText("Tube is not in experiment space");
                noSuchTube.setContentText("Make sure the tube has been added!");
                noSuchTube.showAndWait();
            }
        }

        if (event.getSource() == showExplanation) {
            if (isAnswered()){
                explanationTab.setDisable(false);
            } else {
                Alert noAnswerYet = new Alert(Alert.AlertType.WARNING);
                noAnswerYet.setTitle("Warning");
                noAnswerYet.setHeaderText("Answers have not been submitted yet!");
                noAnswerYet.setContentText("Click check answer to submit!");
                noAnswerYet.showAndWait();
            }
        }
    }

    public void saveQueries(ActionEvent event) {
        if (!isOpenedQueries()){
            try {
                setOpenedQueries(true);
                FXMLLoader fxmlLoader = new FXMLLoader(TestExperimentSpace.class.getResource("note.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.getIcons().add(new Image(TestExperimentSpace.class.getResourceAsStream("chemical.png")));
                Scene scene = new Scene(root);
                stage.setResizable(false);
//            scene.getStylesheets().add(TestExperimentSpace.class.getResource("View/logInPage.css").toExternalForm());
                stage.setScene(scene);
                stage.setTitle("About the Programmer");
                stage.showAndWait();
                if (!stage.isShowing()) {
                    setOpenedQueries(false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }



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
    private MenuItem deleteBlueLitmus;

    @FXML
    private MenuItem deleteRedLitmus;

    @FXML
    private MenuItem deleteTube1;

    @FXML
    private MenuItem deleteTube2;

    @FXML
    private MenuItem deleteTube3;

    @FXML
    private MenuItem deleteBurner;

    @FXML
    private MenuItem deleteLighter;

    @FXML
    private MenuItem deleteSplint;

    @FXML
    private MenuItem clearAll;

    @FXML
    private MenuItem aboutProgrammer;
    private FileChooser fileChooser = new FileChooser();
    public void save(ActionEvent event) {
        fileChooser.setInitialDirectory(new File(System
                .getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter( "*.cqa", "*.CQA"),
                new FileChooser.ExtensionFilter("*.pdf", "*.PDF"));
        fileChooser.setTitle("Save");
        fileChooser.setInitialFileName("Experiment 1");
        final File file = fileChooser.showSaveDialog(anchorPane.getScene().getWindow());

    }

    public void delete(ActionEvent event) {
        components = getExperimentSpace().getChildren();

        Alert toolNotInPane = new Alert(Alert.AlertType.WARNING);
        toolNotInPane.setTitle("Warning");
        toolNotInPane.setHeaderText("Tool cannot be deleted");
        toolNotInPane.setContentText("The tool has not been added to the experiment space!");

        for (Node node : components) {
            tubes.clear();
            if (node instanceof TubePane) {
                tubes.add(node);
            }
        }
        if (event.getSource() == deleteBlueLitmus) {
            if (!getHistory().getBlueLitmus().isEmpty()) {
                getHistory().getBlueLitmus().clear();
                components.removeIf(node -> node instanceof BlueLitmusPane);
            } else {
                toolNotInPane.showAndWait();
            }
        }
        if (event.getSource() == deleteRedLitmus) {
            if (!getHistory().getRedLitmus().isEmpty()) {
                getHistory().getRedLitmus().clear();
                components.removeIf(node -> node instanceof RedLitmusPane);
            } else {
                toolNotInPane.showAndWait();
            }
        }
        if (event.getSource() == deleteTube1) {
            try {
                tubes.remove(0);
                for (int i = 0; i < getExperimentSpace().getChildren().size(); i++) {
                    if (getExperimentSpace().getChildren().get(i) instanceof TubePane) {
                        getExperimentSpace().getChildren().remove(i);
                        break;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                toolNotInPane.showAndWait();
            }
        }
        if (event.getSource() == deleteTube2) {
            try {
                tubes.remove(1);
                int count = 0;
                for (int i = 0; i<getExperimentSpace().getChildren().size(); i++) {
                    if (getExperimentSpace().getChildren().get(i) instanceof TubePane) {
                        if (count == 1) {
                            getExperimentSpace().getChildren().remove(i);
                        }
                        count++;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                toolNotInPane.showAndWait();
            }
        }
        if (event.getSource() == deleteTube3) {
            try {
                tubes.remove(2);
                int count = 0;
                for (int i =0; i <getExperimentSpace().getChildren().size(); i++) {
                    if (getExperimentSpace().getChildren().get(i) instanceof TubePane) {
                        if (count == 2) {
                            getExperimentSpace().getChildren().remove(i);
                        }
                        count++;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                toolNotInPane.showAndWait();
            }
        }
        if (event.getSource() == deleteBurner) {
            if (getHistory().getBunsenBurner() != null){
                getHistory().setBunsenBurner(null);
                components.removeIf(node -> node instanceof BurnerPane);
            } else {
                toolNotInPane.showAndWait();
            }
        }
        if (event.getSource() == deleteLighter) {
            if (getHistory().getBunsenBurner() != null){
                getHistory().setLighter(null);
                components.removeIf(node -> node instanceof LighterPane);
                lightUp.setText("Heat up");
                lightUp.setOnAction(e -> {
                    getHistory().getBunsenBurner().setFire(true);
                    getHistory().getBunsenBurner().setAirHole(true);
                    getHistory().getBunsenBurner().setHeat(true);
                    Alert hotWarning = new Alert(Alert.AlertType.INFORMATION);
                    hotWarning.setTitle("High heat!");
                    hotWarning.setContentText("Bunsen burner is lighted with open air hole");
                    hotWarning.setHeaderText("Be careful when handling bunsen burner");
                    hotWarning.showAndWait();
//                    for (Node node : getExperimentSpace().getChildren()){
//                        if (node instanceof BurnerPane) {
//                            ((BurnerPane) node).paint();
//                            Bounds bounds = getExperimentSpace().getLayoutBounds();
//                            node.setLayoutX((bounds.getMaxX()-bounds.getMinX())/2);
//                            node.setLayoutY((bounds.getMaxY()-bounds.getMinY())/2);
//                        }
//                    }
                });
            } else {
                toolNotInPane.showAndWait();
            }
        }
        if (event.getSource() == deleteSplint) {
            if (!getHistory().getSplint().isEmpty()) {
                getHistory().getSplint().clear();
                components.removeIf(node -> node instanceof SplintPane);
            } else {
                toolNotInPane.showAndWait();
            }
        }
    }

    public void clearAll(ActionEvent event) {
        try {
            blackPan.setVisible(false);
            getExperimentSpace().getChildren().removeIf(node -> !(node instanceof Rectangle));
            getHistory().clearAll();
            setHasTestTube(false);
            setAddedSalt(false);
            blackPanel.setText("Show black panel");
            setAnswered(false);
            setHasHeatingEquipment(false);
            salts.getSelectionModel().clearSelection();
            testPH.getSelectionModel().clearSelection();
            splint.getSelectionModel().clearSelection();
            tube.getSelectedToggle().setSelected(false);

            Alert cleared = new Alert(Alert.AlertType.INFORMATION);
            cleared.setTitle("Successful!");
            cleared.setHeaderText("Clear all succeeded");
            cleared.setContentText("All tools have been deleted!");
            cleared.showAndWait();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param event
     */
    public void aboutPage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TestExperimentSpace.class.getResource("about-programmer.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(TestExperimentSpace.class.getResourceAsStream("chemical.png")));
            Scene scene = new Scene(root);
//            scene.getStylesheets().add(TestExperimentSpace.class.getResource("View/logInPage.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("About the Programmer");
            stage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exit(ActionEvent event) {
        ((Stage) anchorPane.getScene().getWindow()).close();
    }


    // Initialize
    ObservableList<String> saltsForQA = FXCollections.observableArrayList(
            "Random","FeSO4", "MgSO4", "ZnSO4", "CuSO4", "(NH4)2SO4", "CuCO3", "CaCO3", "FeCl3", "NaCl","Cu(NO3)2");
    @FXML
    private Tab explanationTab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        explanationTab.setDisable(true);
        explanation.setText("Explanation goes here!");
        salts.setItems(saltsForQA);
        testPH.setItems(litmusPaperType);
        splint.setItems(splintTypes);
        lightUp.setVisible(false);
        database = new Database("cationNames.txt", "anionNames.txt", "gas.txt");
        history = new History();
        added.setVisible(false);
        aluminiumFoil.setVisible(false);
        blackPan.setVisible(false);
        fileChooser.setInitialDirectory(new File("C:\\temp"));
    }
}
