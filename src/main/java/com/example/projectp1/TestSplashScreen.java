package com.example.projectp1;


import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.*;
import javafx.concurrent.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.image.Image;

import java.io.IOException;

/**
 * Example of displaying a splash page for a standalone JavaFX application
 * modified from https://gist.github.com/jewelsea/2305098
 *
 */
public class TestSplashScreen extends Application {
    public static final String APPLICATION_ICON ="file:avatarChemistry.png";
    public static final String SPLASH_IMAGE ="file:avatarChemistry.png";

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage mainStage;
    private static final int SPLASH_WIDTH = 400;
    private static final int SPLASH_HEIGHT = 200;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(SPLASH_IMAGE));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("Will find friends for peanuts . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 5; " +
                        "-fx-background-color: mistyrose; " +
                        "-fx-border-width:5; " +
                        "-fx-border-color: " +
                        "linear-gradient(" +
                        "to bottom, " +
                        "palevioletred, " +
                        "plum" +
                        ");"
        );
        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        final Task<ObservableList<String>> friendTask = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> foundFriends =
                        FXCollections.<String>observableArrayList();

                updateMessage("loading application. . .");
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(400);
                    updateProgress(i+1,5);
                    updateMessage("loading application . . . found " + (i+1) + "...");
                }
                Thread.sleep(400);
                updateMessage("Done!");

                return foundFriends;
            }
        };

        showSplash(
                initStage,
                friendTask,
                () -> showMainStage()
        );
        new Thread(friendTask).start();
    }

    private void showMainStage() {
        try {
            mainStage = new Stage(StageStyle.DECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader(TestHomePage.class.getResource("home-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setTitle("ChemQAnalytica");
            mainStage.getIcons().add(new Image(APPLICATION_ICON));
//        scene.getStylesheets().add(TestGUI.class.getResource("View/logInPage.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.setResizable(false);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.

        });

        Scene splashScene = new Scene(splashLayout);
        initStage.initStyle(StageStyle.UNDECORATED);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.show();
    }

    public interface InitCompletionHandler {
        public void complete();
    }
}


