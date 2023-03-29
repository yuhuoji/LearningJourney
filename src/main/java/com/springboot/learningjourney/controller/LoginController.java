package com.springboot.learningjourney.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@FXMLController
public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private Button logoutButton;

    @FXML
    void onClicked(ActionEvent event) {
        System.out.println("hello");
    }
}
