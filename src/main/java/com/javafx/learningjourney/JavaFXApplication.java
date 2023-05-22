package com.javafx.learningjourney;

import com.javafx.learningjourney.util.Cache;
import com.javafx.learningjourney.util.RootPathUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The startup class of a JavaFX project, that includes the main method
 */
/*
Running sequence init -> start -> stop
 */
public class JavaFXApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * load a fxml file
     *
     * @param fxml fxml file path
     * @return root node
     */
    public static Node loadFXML(String fxml) {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
//            System.out.println(JavaFXApplication.class.getClassLoader().getResource(fxml));
            fxmlLoader.setLocation(Objects.requireNonNull(JavaFXApplication.class.getClassLoader().getResource(fxml))); //set fxml file path
            //  System.out.println("fxmlLoader.getLocation() = " + fxmlLoader.getLocation());
            root = fxmlLoader.load(); //load fxml file

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FXML " + fxml + " load error.");
        }
        return root;
    }

    @Override
    public void init() throws Exception {
        super.init();
        Cache.put("rootDirectoryPath", RootPathUtil.getRootPath());
        Cache.put("folderRootPath", RootPathUtil.getFolderRootPath());
    }

    @Override
    public void start(Stage primaryStage) {
        Cache.put("stage", primaryStage);

        primaryStage.setTitle("Learning Journey");

        Parent root = (Parent) loadFXML("fxml/LoginView.fxml"); //切换页面
        Cache.put("currentView", "LoginView");

        primaryStage.setScene(new Scene(root)); //set scene
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
