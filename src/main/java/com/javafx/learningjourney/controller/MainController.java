package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import com.javafx.learningjourney.util.Cache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.javafx.learningjourney.JavaFXApplication.*;

public class MainController {
    private final FileDAO fileDAO;
    @FXML
    private Pane mainContent; ////切换页面的位置
    @FXML
    private AnchorPane navbar; //navbar.fxml
    @FXML
    private AnchorPane sidebar; //sidebar.fxml

    public MainController() {
        this.fileDAO = new FileDAOImpl();
    }

    @FXML
    private void initialize() {
        System.out.println("main initialize");

        Cache.put(this.getClass().getSimpleName(), this); //将MainController的引用放入缓存

        Cache.put("mainContent", mainContent); //将页面替换的根节点放入缓存

        loadSidebarTreeView(); //init sidebar

        Cache.put("currentView", "CourseView"); //update current view
        Path currentPath = Paths.get(((Path) Cache.get("currentPath")).toString(), "Course");
        System.out.println("currentPath = " + currentPath);
        Cache.put("currentPath", currentPath); //update current path
        Node newNode = loadFXML("fxml/course/CourseView.fxml");
        System.out.println("newNode = " + newNode);

        Pane root = (Pane) Cache.get("mainContent");
        root.getChildren().clear();
        root.getChildren().add(newNode);

    }

    /**
     * 加载侧边栏的TreeView并设置点击事件
     */
    private void loadSidebarTreeView() {
        System.out.println("loadSidebarTreeView");
        //   System.out.println("currentPath = " + Cache.get("currentPath"));
        //new folders
        fileDAO.createDirectory((Path) Cache.get("currentPath"), "Course");
        fileDAO.createDirectory((Path) Cache.get("currentPath"), "Internship");
        fileDAO.createDirectory((Path) Cache.get("currentPath"), "Research");
        fileDAO.createDirectory((Path) Cache.get("currentPath"), "Work");

        System.out.println("111folderRootPath = " + Cache.get("folderRootPath"));
        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFoldersInCurrentLevel((Path) Cache.get("folderRootPath")); //root

        if (rootItem == null) {
            System.out.println("rootItem is null");
            rootItem = new TreeItem<>(((Path) Cache.get("folderRootPath")).getFileName()); // 创建根节点
        }
        rootItem.setExpanded(true); //set the root expanded

        //通过id获取sidebar中的menuTreeView
        TreeView<Path> menuTreeView = (TreeView<Path>) sidebar.lookup("#menuTreeView");
        //System.out.println("menuTreeView = " + menuTreeView);

        menuTreeView.setRoot(rootItem); //set the root to the TreeView
        menuTreeView.setShowRoot(false); //不显示根节点

        menuTreeView.setOnMouseClicked(event -> { // 处理侧栏点击事件的逻辑
            TreeItem<Path> selectedItem = menuTreeView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Path selectedPath = selectedItem.getValue();
                System.out.println("selectedPath = " + selectedPath);

                Cache.put("currentView", selectedPath + "View"); //update current view
                Cache.put("currentPath", Paths.get(
                        ((Path) (Cache.get("folderRootPath"))).toString(), selectedPath.toString())
                ); //update current path
                Node newNode = loadFXML("fxml/" + selectedPath + "/" + selectedPath + "View.fxml");
                Pane root = (Pane) Cache.get("mainContent");
                root.getChildren().clear();
                root.getChildren().add(newNode);
            }
        });
    }

    @FXML
    public void handleOpen(ActionEvent event) {
        // 处理 "Open" 按钮的点击事件
        // 在这里编写打开文件的逻辑
    }

}
