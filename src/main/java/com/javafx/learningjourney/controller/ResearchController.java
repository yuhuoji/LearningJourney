package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.JavaFXApplication;
import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * research and work controller
 */
public class ResearchController {
    private final FileDAO fileDAO;


    public ResearchController() {
        fileDAO = new FileDAOImpl();
    }

    @FXML
    public void onClick(ActionEvent event) {

    }

    @FXML
    public void initialize() {

    }

    public void onClickTest(ActionEvent actionEvent) {
        System.out.println("ResearchView.fxml");
    }
}
