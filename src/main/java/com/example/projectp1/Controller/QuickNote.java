package com.example.projectp1.Controller;

import com.example.projectp1.TestExperimentSpace;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuickNote implements Initializable {
    @FXML
    private MenuItem saveProgress;
    @FXML
    private MenuItem export;
    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem clearAll;

    @FXML
    private TextArea note;

    @FXML
    private MenuItem aboutProgrammer;

    public void exit(ActionEvent event) {
        note.getScene().getWindow().hide();
    }
    public void clearAll(ActionEvent event) {
        note.clear();
    }

    public void aboutPopUp(ActionEvent event) {
        try {
            if (event.getSource() == aboutProgrammer) {
                FXMLLoader fxmlLoader = new FXMLLoader(TestExperimentSpace.class.getResource("about-programmer.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.getIcons().add(new Image(TestExperimentSpace.class.getResourceAsStream("chemical.png")));
                Scene scene = new Scene(root);
//            scene.getStylesheets().add(TestExperimentSpace.class.getResource("View/logInPage.css").toExternalForm());
                stage.setScene(scene);
                stage.setTitle("Save queries");
                stage.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
