package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import com.javafx.learningjourney.util.Cache;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
    private BorderPane borderPane;
    @FXML
    private AnchorPane navbar; //navbar.fxml
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane sidebar; //sidebar.fxml
    @FXML
    private BorderPane courseView; //CourseView.fxml
    @FXML
    private Button mainTestButton;

    public MainController() {
        this.fileDAO = new FileDAOImpl();
    }

    /**
     * 加载侧边栏的TreeView并设置点击事件
     */
    private void loadSidebarTreeView() {
        System.out.println("loadSidebarTreeView");
     //   System.out.println("currentPath = " + Cache.get("currentPath"));
        //新建文件夹
        fileDAO.createDirectory((Path) Cache.get("currentPath"), "Course");
        fileDAO.createDirectory((Path) Cache.get("currentPath"), "Internship");
        fileDAO.createDirectory((Path) Cache.get("currentPath"), "Research");
        fileDAO.createDirectory((Path) Cache.get("currentPath"), "Work");

        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFoldersInCurrentLevel(folderRootPath); //root

        if (rootItem == null) {
            System.out.println("rootItem is null");
            rootItem = new TreeItem<>(folderRootPath.getFileName()); // 创建根节点
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
                Node newNode = loadFXML("fxml/" + selectedPath + "View.fxml");
                Pane root = (Pane) Cache.get("mainContent");
                root.getChildren().clear();
                root.getChildren().add(newNode);
            }

        });

    }

    //click
    @FXML
    public void onClick() {
        System.out.println("MainView.fxml test");

    }

    @FXML
    private void onClickTestSide() {
        System.out.println("onClickTestSide");
    }

    @FXML
    public void initialize() {
        System.out.println("main initialize");

        Cache.put(this.getClass().getSimpleName(), this); //将MainController的引用放入缓存

        Cache.put("mainContent", mainContent); //将页面替换的根节点放入缓存

        splitPane.setDividerPositions(0.2); //将splitPane分隔条位置设置为20%
        loadSidebarTreeView(); //init sidebar

        Cache.put("currentView", "CourseView"); //update current view
        Path currentPath = Paths.get(((Path) Cache.get("currentPath")).toString(), "Course");
        System.out.println("currentPath = " + currentPath);
        Cache.put("currentPath", currentPath); //update current path
        Node newNode = loadFXML("fxml/CourseView.fxml");
        System.out.println("newNode = " + newNode);

        Pane root = (Pane) Cache.get("mainContent");
        root.getChildren().clear();
        root.getChildren().add(newNode);

    }
}
