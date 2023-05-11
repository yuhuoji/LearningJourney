package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;

import java.nio.file.Path;

import static com.javafx.learningjourney.JavaFXApplication.folderRootPath;

/**
 * course and internship controller
 */
public class CourseController {
    private final FileDAO fileDAO;

    @FXML
    private SplitPane splitPane;


    @FXML
    private StackPane stackPane;

    public CourseController() {
        fileDAO = new FileDAOImpl();
    }

    //初始化数据
    @FXML
    public void initialize() {
/*        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFoldersInCurrentLevel(folderRootPath); //root
//        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFilesAndFolders(folderRootPath); //可以生成所有的文件和文件夹

        if (rootItem == null) {
            rootItem = new TreeItem<>(folderRootPath.getFileName()); // 创建根节点
        }
        rootItem.setExpanded(true); //set the root expanded
        treeView.setRoot(rootItem); //set the root to the TreeView
        treeView.setShowRoot(false); //不显示根节点
        System.out.println("rootItem = " + rootItem);*/


    }
}
