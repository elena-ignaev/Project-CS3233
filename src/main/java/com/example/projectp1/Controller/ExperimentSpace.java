package com.example.projectp1.Controller;

import com.example.projectp1.FXObjects.*;
import com.example.projectp1.Model.*;
import com.example.projectp1.TestExperimentSpace;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
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
                tube.setOnMouseDragged(e -> Platform.runLater(() -> {
                    tube.setLayoutX(e.getSceneX());
                    tube.setLayoutY(e.getSceneY());
                }));
                // shake the tube
                tube.setOnMouseClicked(e -> Platform.runLater(() -> {
                    RotateTransition shake = new RotateTransition(Duration.millis(150));
                    shake.setNode(tube);
                    shake.setFromAngle(0);
                    shake.setToAngle(10);
                    shake.setCycleCount(20);
                    shake.setAutoReverse(true);
                    shake.play();
                    shake.setOnFinished(event1 -> {
                        added.setText("Precipitated!");
                        showAdded();
                    });
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
                    red.setOnMouseDragged(e -> Platform.runLater(() -> {
                        red.setLayoutX(e.getSceneX());
                        red.setLayoutY(e.getSceneY());
                    }));
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
                    blue.setOnMouseDragged(e -> Platform.runLater(() -> {
                        blue.setLayoutX(e.getSceneX());
                        blue.setLayoutY(e.getSceneY());
                    }));
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

    ObservableList<Node> components;
    ArrayList<Node> tubes = new ArrayList<>();


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
        added.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setNode(added);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setAutoReverse(false);
        fade.setCycleCount(1);
        fade.setDelay(Duration.millis(1000));
        fade.setDuration(Duration.millis(1000));
        fade.play();
        fade.setOnFinished(e -> added.setVisible(false));
    }


    /*
    Add substance to test tube
    Requirements:
    - There is at least one test tube in the experiment space
    - There is a selected test tube for adding the substances
    - The selected test tube already contains salt to be test on
     */
    public void addSubstance(ActionEvent event) {
        try {
            if (isHasTestTube()) {
                // "Random","FeSO4", "MgSO4", "ZnSO4", "CuSO4", "(NH4)2SO4", "CuCO3", "CaCO3", "FeCl3", "NaCl","Cu(NO3)2"
                if (event.getSource() == salts) {
                    resetTube();
                    setAddedSalt(true);
                    if (salts.getValue() != null){
                        ((TubePane) tubes.get(findTubeIndex())).setTransparency3(0.5);
                        if (salts.getValue().equals("Random")) {
                            resetTube();
                            Random random = new Random();
                            int cationIndex = random.nextInt(Database.getNumOfCations());
                            int anionIndex = random.nextInt(Database.getNumOfAnions());
                            Salt randomSalt = new Salt(new Cation(cationIndex, getDatabase()), new Anion(anionIndex, getDatabase()));
                            System.out.println(randomSalt.getName());

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
                        if (tube.getSelectedToggle() != null) {
                            showAdded();
                        }
                    }
                }
                if (isAddedSalt()) {
                    /*
                    TODO:
                    - make exception for adding NaOH to ammonium salts (releasing NH3 instead)
                     */
                    FadeTransition precipitate = new FadeTransition();
                    precipitate.setFromValue(0.25);
                    precipitate.setToValue(1);
                    precipitate.setDuration(Duration.millis(2000));
                    precipitate.setCycleCount(1);
                    precipitate.setAutoReverse(false);
                    if (event.getSource() == aqueousNaOH) {
                        Platform.runLater(() -> {
                            resetTube();
                            TestingChemicals NaOH = new TestingChemicals("NaOH");
                            Layer aqueousNaOH = new Layer(NaOH, "lightgrey", false);
                            getHistory().getTubes().get(findTubeIndex()).setLayer3(aqueousNaOH);
                            ((TubePane) tubes.get(findTubeIndex())).setTransparency3(0.25);
                            precipitate.setNode(((TubePane) tubes.get(findTubeIndex())).getLayer3());
                            precipitate.setDelay(Duration.millis(100));
                            precipitate.play();
                        });
                    }
                    if (event.getSource() == aqueousNH3) {
                        Platform.runLater(()->{
                            resetTube();
                            TestingChemicals NH3 = new TestingChemicals("NH3");
                            Layer aqueousNH3 = new Layer(NH3, "lightgrey", false);
                            getHistory().getTubes().get(findTubeIndex()).setLayer3(aqueousNH3);
                            ((TubePane) tubes.get(findTubeIndex())).setTransparency3(0.25);
                            precipitate.play();
                        });
                    }
                    if (event.getSource() == aqueousAgNO3) {
                        Platform.runLater(() -> {
                            resetTube();
                            TestingChemicals AgNO3 = new TestingChemicals("AgNO3");
                            Layer aqueousAgNO3 = new Layer(AgNO3, "lightgrey", false);
                            getHistory().getTubes().get(findTubeIndex()).setLayer3(aqueousAgNO3);
                            ((TubePane) tubes.get(findTubeIndex())).setTransparency3(0.25);
                        });
                    }
                    if (event.getSource() == aqueousBaCl2) {
                        Platform.runLater(() -> {
                            resetTube();
                            TestingChemicals BaCl2 = new TestingChemicals("BaCl2");
                            Layer aqueousBaCl2 = new Layer(BaCl2, "lightgrey", false);
                            getHistory().getTubes().get(findTubeIndex()).setLayer3(aqueousBaCl2);
                            ((TubePane) tubes.get(findTubeIndex())).setTransparency3(0.25);
                        });
                    }
                    if (tube.getSelectedToggle() != null) {
                        showAdded();
                    }
                } else {
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
                salts.setValue(null);
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
    private RadioButton questionTube1;
    @FXML
    private RadioButton questionTube2;
    @FXML
    private RadioButton questionTube3;
    public void question(ActionEvent event) {
        if (event.getSource() == checkAns) {
            String cation = cationAns.getText();
            String anion = anionAns.getText();
            String salt = saltAns.getText();

        }

        if (event.getSource() == showExplanation) {

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
//            scene.getStylesheets().add(TestExperimentSpace.class.getResource("View/logInPage.css").toExternalForm());
                stage.setScene(scene);
                stage.setTitle("About the Programmer");
                stage.showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {

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
    private MenuItem delete;

    @FXML
    private MenuItem clearAll;

    @FXML
    private MenuItem aboutProgrammer;

    public void clearAll(ActionEvent event) {
        getExperimentSpace().getChildren().clear();
        getHistory().clearAll();
    }

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


    // Initialize
    ObservableList<String> saltsForQA = FXCollections.observableArrayList(
            "Random","FeSO4", "MgSO4", "ZnSO4", "CuSO4", "(NH4)2SO4", "CuCO3", "CaCO3", "FeCl3", "NaCl","Cu(NO3)2");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        salts.setItems(saltsForQA);
        testPH.setItems(litmusPaperType);
        splint.setItems(splintTypes);
        database = new Database("cationNames.txt", "anionNames.txt", "gas.txt");
        history = new History();
        added.setVisible(false);
    }

}
