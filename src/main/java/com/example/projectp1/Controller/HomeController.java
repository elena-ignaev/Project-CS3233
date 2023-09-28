package com.example.projectp1.Controller;

import com.example.projectp1.TestExperimentSpace;
import com.example.projectp1.TestHomePage;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HomeController {
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
    private MenuItem findSpace;

    @FXML
    private MenuItem exitHome;
    @FXML
    private MenuItem changeImage;
    @FXML
    private MenuItem rename;
    @FXML
    private MenuItem delete;


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void createNew(ActionEvent event) {
        if (event.getSource() == newSpace){
            try {
                setStage((Stage)anchorPane.getScene().getWindow());
                Stage stage2 = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(TestExperimentSpace.class.getResource("experiment-space.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                stage2.setTitle("ChemQAnalytica");
                stage2.getIcons().add(new Image(TestHomePage.class.getResourceAsStream("chemical.png")));
//        scene.getStylesheets().add(TestExperimentSpace.class.getResource("light-mode.css").toExternalForm());
                stage2.setScene(scene);
                stage2.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Button button = new Button();
            button.setPrefWidth(80.0);
            button.setPrefHeight(80.0);
            Image image = new Image(TestHomePage.class.getResourceAsStream("avatarChemistry.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80.0);
            imageView.setFitHeight(80.0);
            imageView.setPreserveRatio(true);
            button.setGraphic(imageView);
            experimentGrid1.add(button, 3,0);
            button.setAlignment(Pos.CENTER);
        }

        if (event.getSource() == newNote) {
            try {
                setStage((Stage) anchorPane.getScene().getWindow());
                Stage stage2 = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(TestExperimentSpace.class.getResource("note.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage2.setResizable(false);
                stage2.setTitle("ChemQAnalytica");
                stage2.getIcons().add(new Image(TestExperimentSpace.class.getResourceAsStream("chemical.png")));
//        scene.getStylesheets().add(TestExperimentSpace.class.getResource("light-mode.css").toExternalForm());
                stage2.setScene(scene);
                stage2.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Button button = new Button();
            button.setPrefWidth(80.0);
            button.setPrefHeight(80.0);
            button.getChildrenUnmodifiable().add(new ImageView("avatarChemistry.png"));
        }

    }

    public void openSearch(ActionEvent event) {

    }

    public void endProgram(ActionEvent event) {

    }

    public void edit(ActionEvent event) {

    }

    public void accessNote(ActionEvent event) {
        if (event.getSource() == QAnotes) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Desktop.getDesktop().open(new File("QA Notes.pdf"));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void accessSpace() {

    }

}
