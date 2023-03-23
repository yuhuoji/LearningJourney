package com.group60.learningjourney;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("按钮A using JDK-" + System.getProperty("java.version"));
        VBox vBox = new VBox(btn);
        vBox.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(vBox, 640, 360));
        primaryStage.setTitle("Maven Test");
        primaryStage.show();
    }
}
