package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.JavaFXApplication;
import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import static com.javafx.learningjourney.JavaFXApplication.rootDirectoryPath;

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
        JavaFXApplication.changeView("fxml/home.fxml"); //切换页面
    }

    @FXML
    public void initialize() {
        text.setText("Hello World!");
    }
}
