package com.example.projectp1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class ArcTest extends Application {
    @Override
    public void start(Stage stage) {
        Arc arc = new Arc(100,200,80,80,90,80);
        arc.setType(ArcType.OPEN);
        arc.setFill(Color.RED);
        arc.setStroke(Color.BLACK);
        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(arc);
        Scene scene = new Scene(pane, 400,600);
        stage.setScene(scene);
        stage.setTitle("Test arc");
        stage.show();
    }
}
