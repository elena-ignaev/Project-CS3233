package com.example.projectp1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class TestGUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TestGUI.class.getResource("experiment-space.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        scene.getStylesheets().add(TestGUI.class.getResource("View/logInPage.css").toExternalForm());
        stage.setTitle("My Chemical");
        stage.getIcons().add(new Image(TestGUI.class.getResourceAsStream("chemical.png")));
//        scene.getStylesheets().add(TestGUI.class.getResource("View/logInPage.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
