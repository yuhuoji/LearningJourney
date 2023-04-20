package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.nio.file.Path;

import static com.javafx.learningjourney.JavaFXApplication.folderRootPath;

public class HomeController {
    private final FileDAO fileDAO;

    @FXML
    private BorderPane rootPane;

    @FXML
    private VBox leftPane;

    @FXML
    private TreeView<Path> learningJourneyFilesTreeView;

    @FXML
    private StackPane mainPane;

    @FXML
    private TreeView<?> mainTreeView;

    @FXML
    private HBox topPane;

    @FXML
    private Text title;

    @FXML
    private Button button;

    @FXML
    private VBox rightPane;

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
        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFoldersInCurrentLevel(folderRootPath); //root
//        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFilesAndFolders(folderRootPath); //可以生成所有的文件和文件夹

        if (rootItem == null) {
            rootItem = new TreeItem<>(folderRootPath.getFileName()); // 创建根节点
        }
        rootItem.setExpanded(true); //set the root expanded
        learningJourneyFilesTreeView.setRoot(rootItem); //set the root to the TreeView
        System.out.println("rootItem = " + rootItem);


    }
}
