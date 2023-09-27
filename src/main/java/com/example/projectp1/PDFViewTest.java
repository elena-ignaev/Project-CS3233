package com.example.projectp1;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PDFViewTest extends Application {
    @Override
    public void start(final Stage primaryStage) {
        final HBox root = new HBox(5);
        root.setPadding(new Insets(5));
        final Button button = new Button("Browse");
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System
                .getProperty("user.home")));
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("PDF Files", "*.pdf", "*.PDF"));


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Desktop.getDesktop().open(file);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }

        });

        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root, 150, 75));
        primaryStage.show();
    }



    public static void main(String[] args) throws Exception {
        System.setProperty("javafx.macosx.embedded", "true");
        Toolkit.getDefaultToolkit();
        launch(args);


    }


}
