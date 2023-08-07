package com.example.projectp1;

import com.example.projectp1.FXObjects.*;
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

        RedLitmusPane redLitmusPane = new RedLitmusPane();
        BurnerPane burnerPane = new BurnerPane();
        SplintPane splintPane = new SplintPane();

        AnchorPane pane = new AnchorPane();

        pane.getChildren().addAll(tubePane, lighterPane, redLitmusPane, burnerPane, splintPane);

        tubePane.setLayoutX(100);
        tubePane.setLayoutY(100);

        redLitmusPane.setLayoutX(200);
        redLitmusPane.setLayoutY(100);

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