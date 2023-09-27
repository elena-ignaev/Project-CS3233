package com.example.projectp1.Controller;

import com.example.projectp1.TestExperimentSpace;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HomeController {
    Stage stage;
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

    public void createNew() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TestExperimentSpace.class.getResource("experiment-space.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            stage.setTitle("ChemQAnalytica");
            stage.getIcons().add(new Image(TestExperimentSpace.class.getResourceAsStream("chemical.png")));
//        scene.getStylesheets().add(TestExperimentSpace.class.getResource("light-mode.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button button = new Button();
        button.setPrefWidth(80.0);
        button.setPrefHeight(80.0);
        button.getChildrenUnmodifiable().add(new ImageView("avatarChemistry"));

    }

    public void accessNote() {

    }

    public void accessSpace() {

    }

}
