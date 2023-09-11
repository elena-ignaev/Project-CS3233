package com.example.projectp1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TestHomePage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TestHomePage.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ChemQAnalytica");
//        scene.getStylesheets().add(TestGUI.class.getResource("View/logInPage.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
