package com.javafx.learningjourney.controller.component;

import com.javafx.learningjourney.util.Cache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NavbarController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem openMenuItem;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private void initialize() {

    }

    /**
     * Open the folder
     * @param actionEvent click event
     */
    @FXML
    public void handleOpen(ActionEvent actionEvent) {
        System.out.println("openMenuItem clicked");
        openFolder(Cache.get("folderRootPath").toString());
    }


    /**
     * Open the help file
     * @param actionEvent click event
     */
    @FXML
    public void handleHelp(ActionEvent actionEvent){
        String currentDirectory = Cache.get("rootDirectoryPath").toString();
        Path readmePath = Paths.get(currentDirectory).getParent().resolve("README.md");
        openFile(readmePath.toFile());
    }

    /**
     * Open the link
     * @param actionEvent click event
     */
    @FXML
    public void handleAbout(ActionEvent actionEvent) {
        openURL("https://github.com/yuhuoji/LearningJourney");
    }

    private void openFolder(String folderPath) {
        try {
            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                Desktop.getDesktop().open(folder);
            } else {
                System.out.println("指定的文件夹不存在或不是一个有效的文件夹。");
            }
        } catch (IOException e) {
            System.out.println("无法打开文件夹：" + e.getMessage());
        }
    }

    private void openFile(File file) {
        try {
            if (file.exists() && file.isFile()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("指定的文件不存在或不是一个有效的文件。");
            }
        } catch (IOException e) {
            System.out.println("无法打开文件：" + e.getMessage());
        }
    }

    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
