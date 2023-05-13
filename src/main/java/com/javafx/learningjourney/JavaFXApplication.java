package com.javafx.learningjourney;

import com.javafx.learningjourney.util.RootPathUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

//运行顺序 init -> start -> stop
public class JavaFXApplication extends Application {

    public static Path rootDirectoryPath; //root path
    public static Path folderRootPath; //file storage path
    private static Stage stage; //主舞台

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

    /**
     * FIXME
     * load fxml file
     *
     * @param fxml      fxml file path
     * @param controller controller
     * @return root node
     */
    public static Node loadFXML(String fxml, Object controller) {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            System.out.println(JavaFXApplication.class.getClassLoader().getResource(fxml));
            fxmlLoader.setLocation(Objects.requireNonNull(JavaFXApplication.class.getClassLoader().getResource(fxml))); //set fxml file path
            fxmlLoader.setController(controller);
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
        rootDirectoryPath = RootPathUtil.getRootPath();
        folderRootPath = RootPathUtil.getFolderRootPath();
//        System.out.println("rootDirectoryPath = " + rootDirectoryPath + ", fileStoragePath = " + fileStoragePath);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) {
        JavaFXApplication.stage = primaryStage;
        stage.setTitle("Learning Journey");

        Parent root = (Parent) loadFXML("fxml/LoginView.fxml"); //切换页面
        stage.setScene(new Scene(root)); //set scene
        primaryStage.show();
    }
}
