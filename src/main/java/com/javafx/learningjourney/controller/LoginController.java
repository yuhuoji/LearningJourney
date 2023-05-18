package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import com.javafx.learningjourney.util.Cache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

//        System.out.println("update currentPath " + Cache.get("folderRootPath"));
        Cache.put("currentPath", Cache.get("folderRootPath"));

        Node root = loadFXML("fxml/MainView.fxml"); //切换页面

        ((Stage) Cache.get("stage")).setScene(new Scene((Parent) root));

    }

    @FXML
    private void initialize() {
        text.setText("Hello World!");
        Cache.put(this.getClass().getSimpleName(), this); //将当前LoginController的引用放入缓存
        controllers.put(this.getClass().getSimpleName(), this);
    }
}
