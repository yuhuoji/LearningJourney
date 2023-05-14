package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import static com.javafx.learningjourney.JavaFXApplication.*;

public class LoginController {
    private final FileDAO fileDAO;
    @FXML
    private Button enterButton;
    @FXML
    private Text text;

    public LoginController() {
        fileDAO = new FileDAOImpl();
    }

    @FXML
    public void onClick(ActionEvent event) {
//        System.out.println("rootDirectoryPath = " + rootDirectoryPath);
        fileDAO.createDirectory(rootDirectoryPath, "LearningJourneyFiles"); //如果没有资料文件夹则新建
        fileDAO.createDirectory(rootDirectoryPath, "LearningJourneyFiles");
        fileDAO.createDirectory(rootDirectoryPath, "LearningJourneyFiles");
        fileDAO.createDirectory(rootDirectoryPath, "LearningJourneyFiles");
        fileDAO.createDirectory(rootDirectoryPath, "LearningJourneyFiles");

        Parent root = (Parent) loadFXML("fxml/MainView.fxml"); //切换页面
        getStage().setScene(new Scene(root));
    }

    @FXML
    private void initialize() {
        text.setText("Hello World!");
        controllers.put(this.getClass().getSimpleName(), this);
    }
}
