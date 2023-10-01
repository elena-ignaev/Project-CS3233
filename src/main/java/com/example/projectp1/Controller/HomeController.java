package com.example.projectp1.Controller;

import com.example.projectp1.FXObjects.homePageButton;
import com.example.projectp1.TestExperimentSpace;
import com.example.projectp1.TestHomePage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    private Stage stage;
    @FXML
    private Button newSpace;

    @FXML
    private Button experiment1;

    @FXML
    private Button experiment2;

    @FXML
    private Button newNote;

    @FXML
    private Button safetyPrecautions;

    @FXML
    private Button QAnotes;

    @FXML
    private GridPane experimentGrid1;

    @FXML
    private GridPane experimentGrid2;

    @FXML
    private GridPane noteGrid;
    @FXML
    private MenuItem createNewSpace;
    @FXML
    private MenuItem exitHome;
    @FXML
    private MenuItem delete;
    @FXML
    private MenuItem about;
    private int countExperiment=0;
    private int countNotes=2;
    public int getCountExperiment() {
        return countExperiment;
    }
    public int getCountNotes() {
        return countNotes;
    }
    public void setCountExperiment(int countExperiment) {
        this.countExperiment = countExperiment;
    }
    public void setCountNotes(int countNotes) {
        this.countNotes = countNotes;
    }
    public void aboutPopUp(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TestHomePage.class.getResource("about-programmer.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(TestHomePage.class.getResourceAsStream("chemical.png")));
            Scene scene = new Scene(root);
//            scene.getStylesheets().add(TestExperimentSpace.class.getResource("View/logInPage.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("About the Programmer");
            stage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void createNew(ActionEvent event) {
        if (event.getSource() == newSpace || event.getSource() == createNewSpace){
            try {
                setStage((Stage)anchorPane.getScene().getWindow());
                Stage stage2 = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(TestExperimentSpace.class.getResource("experiment-space.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                stage2.setTitle("ChemQAnalytica");
                stage2.getIcons().add(new Image(TestHomePage.class.getResourceAsStream("chemical.png")));
//        scene.getStylesheets().add(TestExperimentSpace.class.getResource("light-mode.css").toExternalForm());
                stage2.setScene(scene);
                Pane newSpace = new homePageButton("My experiment " + (getCountExperiment()+1));
                if (((ExperimentSpace)fxmlLoader.getController()).isSaved()) {
                    setCountExperiment(getCountExperiment()+1);
                }
                if (getCountExperiment()<5){
                    stage2.showAndWait();
                    if (((ExperimentSpace)fxmlLoader.getController()).isSaved()) {
                        experimentGrid1.add(newSpace, getCountExperiment()+1, 0);
                    }
                } else if (getCountExperiment()<10) {
                    stage2.showAndWait();
                    if (((ExperimentSpace)fxmlLoader.getController()).isSaved()) {
                        experimentGrid2.add(newSpace, getCountExperiment() - 5, 0);
                    }
                } else if (getCountExperiment()>=10) {
                    Alert exceededLimit = new Alert(Alert.AlertType.WARNING);
                    exceededLimit.setTitle("Warning");
                    exceededLimit.setHeaderText("Cannot add more experiment space");
                    exceededLimit.setContentText("You can only have a maximum of 9 spaces in this application!");
                    exceededLimit.showAndWait();
                }
                ((Button) newSpace.getChildren().get(0)).setOnAction(e -> {
                    stage2.showAndWait();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

//            if(getCount()<experimentGrid1.getColumnCount()) {
//                if (getCount() == 0){
//                    setCount(3);
//                }
//                experimentGrid1.add(button,experimentGrid1.getColumnCount(),experimentGrid1.getRowCount());
//                setCount(getCount()+1);
//                if (getCount() == experimentGrid1.getColumnCount()) {
//                    setCount(0);
//                    if (getCount()<experimentGrid2.getColumnCount()) {
//                        experimentGrid2.add(button,experimentGrid2.getColumnCount(),experimentGrid2.getRowCount());
//                        setCount(getCount()+1);
//                    }
//                }
//            }
        }

        if (event.getSource() == newNote) {
            setCountNotes(getCountNotes()+1);
            if (getCountNotes()==3){
                try {
                    File file = new File(System.getProperty("user.dir") + "/notes1.txt");
                    Desktop.getDesktop().open(file);
                    Pane pane = new homePageButton("New note " + (getCountNotes() - 2));
                    if (getCountNotes() < 5) {
                        noteGrid.add(pane, getCountNotes(), 0);
                    } else {
                        Alert exceededLimit = new Alert(Alert.AlertType.WARNING);
                        exceededLimit.setTitle("Warning");
                        exceededLimit.setHeaderText("Cannot add more notes");
                        exceededLimit.setContentText("You can only have a maximum of 2 notes in this application!");
                        exceededLimit.showAndWait();
                    }
                    ((Button) pane.getChildren().get(0)).setOnAction(e -> {
                        try {
                            Desktop.getDesktop().open(file);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (getCountNotes()==4) {
                try {
                    File file = new File(System.getProperty("user.dir") + "/notes2.txt");
                    Desktop.getDesktop().open(file);
                    Pane pane = new homePageButton("New note " + (getCountNotes() - 2));
                    if (getCountNotes() < 5) {
                        noteGrid.add(pane, getCountNotes(), 0);
                    } else {
                        Alert exceededLimit = new Alert(Alert.AlertType.WARNING);
                        exceededLimit.setTitle("Warning");
                        exceededLimit.setHeaderText("Cannot add more notes");
                        exceededLimit.setContentText("You can only have a maximum of 2 notes in this application!");
                        exceededLimit.showAndWait();
                    }
                    ((Button) pane.getChildren().get(0)).setOnAction(e -> {
                        try {
                            Desktop.getDesktop().open(file);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void openSearch(ActionEvent event) {
        try {
            setStage((Stage) anchorPane.getScene().getWindow());
            Stage stage2 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(TestHomePage.class.getResource("search-query.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage2.setResizable(false);
            stage2.setTitle("ChemQAnalytica");
            stage2.getIcons().add(new Image(TestHomePage.class.getResourceAsStream("chemical.png")));
//        scene.getStylesheets().add(TestExperimentSpace.class.getResource("light-mode.css").toExternalForm());
            stage2.setScene(scene);
            stage2.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endProgram(ActionEvent event) {
        ((Stage) anchorPane.getScene().getWindow()).close();
    }

    public void edit(ActionEvent event) {
        if (event.getSource() == delete) {
            experimentGrid1.getChildren().removeIf(node -> node instanceof homePageButton);
            experimentGrid2.getChildren().removeIf(node -> node instanceof homePageButton);
            noteGrid.getChildren().removeIf(node -> node instanceof homePageButton);

        }

    }

    public void accessNote(ActionEvent event) {
        if (event.getSource() == QAnotes) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        File file = new File("QA Notes.pdf");
                        file.setWritable(false);
                        Desktop.getDesktop().open(file);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        if (event.getSource() == safetyPrecautions) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Desktop.getDesktop().open(new File("safety-precautions.pdf"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void accessSpace() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCountExperiment(0);
        setCountNotes(2);
    }
}
