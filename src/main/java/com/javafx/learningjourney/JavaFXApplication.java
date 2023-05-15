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
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//运行顺序 init -> start -> stop
public class JavaFXApplication extends Application {
    public static Path rootDirectoryPath; //root path
    public static Path folderRootPath; //file storage path
    private static Stage stage; //主舞台
    public static Map<String, Object> controllers = new HashMap<>(); //存储所有控制器的引用
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * load fxml file
     *
     * @param fxml fxml file path
     * @return root node
     */
    public static Node loadFXML(String fxml) {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            //System.out.println(JavaFXApplication.class);
            System.out.println(JavaFXApplication.class.getClassLoader().getResource(fxml));
            fxmlLoader.setLocation(Objects.requireNonNull(JavaFXApplication.class.getClassLoader().getResource(fxml))); //set fxml file path
            //  System.out.println("fxmlLoader.getLocation() = " + fxmlLoader.getLocation());
            root = fxmlLoader.load(); //load fxml file

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FXML " + fxml + " load error.");
        }
        return root;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        JavaFXApplication.stage = stage;
    }

    @Override
    public void init() throws Exception {
        super.init();
        Cache.put("rootDirectoryPath", RootPathUtil.getRootPath());
        rootDirectoryPath = RootPathUtil.getRootPath();
        Cache.put("folderRootPath", RootPathUtil.getFolderRootPath());
        folderRootPath = RootPathUtil.getFolderRootPath();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) {
        JavaFXApplication.stage = primaryStage;
        Cache.put("stage", primaryStage);
        stage.setTitle("Learning Journey");

        Parent root = (Parent) loadFXML("fxml/LoginView.fxml"); //切换页面
        Cache.put("currentView", "LoginView");

        stage.setScene(new Scene(root)); //set scene
        primaryStage.show();
    }
}
