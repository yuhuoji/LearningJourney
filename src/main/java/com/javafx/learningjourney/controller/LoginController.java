package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.JavaFXApplication;
import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class LoginController {
    private FileDAO fileDAO = new FileDAOImpl();
    @FXML
    private Button enterButton;

    @FXML
    private Text text;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button checkButton;


    @FXML
    public void onClick(ActionEvent event) {
        System.out.println("enter");
        System.out.println("root = " + fileDAO.getRootDirectoryPath()); //打印根目录

        //切换页面
        JavaFXApplication.changeView("fxml/home.fxml");
    }

    @FXML
    public void initialize() {
    }
}
