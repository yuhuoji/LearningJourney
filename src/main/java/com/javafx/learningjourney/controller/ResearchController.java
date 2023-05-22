package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.JavaFXApplication;
import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class ResearchController {
    private final FileDAO fileDAO;
    @FXML
    public BorderPane mainContent;

    public ResearchController() {
        fileDAO = new FileDAOImpl();
    }


    @FXML
    public void initialize() {

    }

    public void onClickTest(ActionEvent actionEvent) {
        System.out.println("ResearchView.fxml");
    }
}
