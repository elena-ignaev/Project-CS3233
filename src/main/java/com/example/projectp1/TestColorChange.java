package com.example.projectp1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TestColorChange extends Application {
    public Color getColor(String color) {
        return Color.web(color);
    }
    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = new AnchorPane();
        Rectangle rect = new Rectangle(50,100,getColor("aquamarine"));
        pane.getChildren().add(rect);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Test color change");
        stage.show();
    }
}
