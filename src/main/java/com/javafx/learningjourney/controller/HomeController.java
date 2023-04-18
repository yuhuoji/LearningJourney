package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class HomeController {
    private final FileDAO fileDAO;
    @FXML
    private AnchorPane home;
    @FXML
    private TreeView<?> treeView;
    @FXML
    private Text title;
    @FXML
    private Button button;

    public HomeController() {
        fileDAO = new FileDAOImpl();
    }

    @FXML
    void onClick(ActionEvent event) {
        System.out.println("click");
    }

    //初始化数据
    @FXML
    public void initialize() {
        TreeView<String> tree = new TreeView<>(); //create a TreeView
//        TreeItem<String> rootItem = fileDAO.getAllFiles(); //root
//        rootItem.setExpanded(true); //set the root expanded


    }
}
