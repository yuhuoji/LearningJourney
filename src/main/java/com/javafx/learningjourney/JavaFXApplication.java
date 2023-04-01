package com.javafx.learningjourney;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JavaFXApplication extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 切换页面
     * @param fxml 切换到的fxml文件路径
     */
    public static void changeView(String fxml) {
        System.out.println(JavaFXApplication.class.getClassLoader().getResource(fxml));
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Objects.requireNonNull(JavaFXApplication.class.getClassLoader().getResource(fxml)));
            root = fxmlLoader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        JavaFXApplication.stage = primaryStage;
        stage.setTitle("welcome");

        changeView("fxml/login.fxml"); //切换页面

        primaryStage.show();
    }
}
