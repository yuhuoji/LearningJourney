package com.group60.learningjourney;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class JavaFXApplication extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/layout.fxml")));
        primaryStage.setTitle("test 窗口");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

}
