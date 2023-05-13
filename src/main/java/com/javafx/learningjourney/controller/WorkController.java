package com.javafx.learningjourney.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class WorkController {

    @FXML
    public BorderPane mainContent;

    public void onClickTest(ActionEvent actionEvent) {
        System.out.println("WorkView.fxml");
    }
}
