package com.example.projectp1;

import com.example.projectp1.FXObjects.*;
import com.example.projectp1.Model.Lighter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TestFXObjects extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        TubePane tubePane = new TubePane();
        LighterPane lighterPane = new LighterPane();

        LitmusPane litmusPane = new LitmusPane();
        BurnerPane burnerPane = new BurnerPane();
        SplintPane splintPane = new SplintPane();

        AnchorPane pane = new AnchorPane();

        pane.getChildren().addAll(tubePane, lighterPane, litmusPane, burnerPane, splintPane);

        tubePane.setLayoutX(100);
        tubePane.setLayoutY(100);

        litmusPane.setLayoutX(200);
        litmusPane.setLayoutY(100);

        burnerPane.setLayoutY(500);
        burnerPane.setLayoutX(100);

        splintPane.setLayoutX(50);
        splintPane.setLayoutY(300);



        Scene scene = new Scene(pane);
        stage.setTitle("My Chemical");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}