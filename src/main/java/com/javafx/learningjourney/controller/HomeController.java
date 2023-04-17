package com.javafx.learningjourney.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class HomeController {
    @FXML
    private AnchorPane home;

    @FXML
    private TreeView<?> treeView;

    @FXML
    private Text title;

    @FXML
    private Button button;

    @FXML
    void onClick(ActionEvent event) {
        System.out.println("click");
    }

}
