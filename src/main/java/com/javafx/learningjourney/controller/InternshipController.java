package com.javafx.learningjourney.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class InternshipController {
    @FXML
    public Button internshipViewTestButton;
    @FXML
    public BorderPane internshipBorderPane;

    @FXML
    public void initialize() {
        System.out.println("internship initialize");
    }

    @FXML
    public void onClickTest() {
        System.out.println("InternshipView.fxml");
    }
}
