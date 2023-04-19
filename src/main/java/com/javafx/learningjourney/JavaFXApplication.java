package com.javafx.learningjourney;

import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

//运行顺序 init -> start -> stop
public class JavaFXApplication extends Application {

    public static Path rootDirectoryPath; //root path
    public static Path fileStoragePath; //file storage path
    private static Stage stage; //主舞台


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 切换页面
     *
     * @param fxml 切换到的fxml文件路径
     */
    public static void changeView(String fxml) {
//        System.out.println(JavaFXApplication.class.getClassLoader().getResource(fxml)); //打印fxml文件路径
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Objects.requireNonNull(JavaFXApplication.class.getClassLoader().getResource(fxml)));
            root = fxmlLoader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FXML load error.");
        }

    }

    @Override
    public void init() throws Exception {
        super.init();
        rootDirectoryPath = new FileDAOImpl().getRootDirectoryPath();
        fileStoragePath = rootDirectoryPath.resolve("LearningJourneyFiles");
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

        changeView("fxml/login.fxml"); //切换页面

        primaryStage.show();
    }
}
