package com.example.projectp1.FXObjects;

import com.example.projectp1.TestHomePage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import javafx.scene.image.ImageView;

public class homePageButton extends Pane implements Paintable {
    private String name = "My experiment";
    private String imagePath = "avatarChemistry.png";
    public homePageButton(String name) {
        this.name = name;
        paint();
    }
    public homePageButton(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
        paint();
    }

    @Override
    public void paint() {
        Button button = new Button();
        Image image = new Image(TestHomePage.class.getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        Label label = new Label(name);
        setPrefHeight(100);
        setPrefWidth(120);
        getStyleClass().add("home-tile");
        imageView.setFitHeight(70.0);
        imageView.setFitWidth(70.0);
        button.setPrefHeight(80.0);
        button.setPrefWidth(80.0);
        button.getStyleClass().add("home-tile-button");
        label.getStyleClass().add("home-tile-label");
        button.setGraphic(imageView);
        button.setAlignment(Pos.CENTER);
        getChildren().addAll(button, label);
        button.setLayoutX(17);
        button.setLayoutY(17);
        label.setLayoutX(21);
        label.setLayoutY(97);
    }
//    Pane pane = new Pane();
//        pane.setPrefHeight(100);
//        pane.setPrefWidth(120);
//
//    Button button = new Button();
//    Image image = new Image(TestHomePage.class.getResourceAsStream("avatarChemistry.png"));
//    javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);
//        imageView.setFitWidth(70.0);
//        imageView.setFitHeight(70.0);
//        button.setPrefWidth(80.0);
//        button.setPrefHeight(80.0);
////            imageView.setPreserveRatio(true);
//        button.setGraphic(imageView);
//        button.setAlignment(Pos.CENTER);
//        pane.getChildren().add(button);
//        button.setLayoutX(17);
//        button.setLayoutY(17);
//
//    Label label = new Label(name);
//        pane.getChildren().add(label);
//        label.setLayoutX(21);
//        label.setLayoutY(97);
//
//        return pane;
}
