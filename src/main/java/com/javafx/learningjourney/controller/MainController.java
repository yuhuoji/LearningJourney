package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.JavaFXApplication;
import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.javafx.learningjourney.JavaFXApplication.folderRootPath;
import static com.javafx.learningjourney.JavaFXApplication.loadFXML;

public class MainController {

    public static MainController mainController; //存储父控制器的引用
    private final FileDAO fileDAO;
    @FXML
    private AnchorPane mainContent; ////切换页面的位置
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
        mainController = this;
    }

    /**
     * 用于替换<AnchorPane fx:id="mainContent"/>的内容
     * @param newNode 要替换的内容
     */
    @FXML
    public void replaceMainContent(Node newNode) {
        ObservableList<Node> items = splitPane.getItems();
        Node oldNode = null;
        for (Node node : items) {
            if (node.getId() != null && node.getId().equals("mainContent")) {
                oldNode = node;
                break;
            }
        }
        if (oldNode != null) {
            System.out.println("newNode = " + newNode);
            items.set(items.indexOf(oldNode), newNode);
        }
    }

    /**
     * 加载侧边栏的TreeView并设置点击事件
     */
    private void loadSidebarTreeView() {
        System.out.println("loadSidebarTreeView");

        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFoldersInCurrentLevel(folderRootPath); //root

        if (rootItem == null) {
            System.out.println("rootItem is null");
            rootItem = new TreeItem<>(folderRootPath.getFileName()); // 创建根节点
        } else {
            System.out.println("rootItem = " + rootItem);
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
                ObservableList<Node> items = splitPane.getItems();
                Node oldNode = null;
                for (Node node : items) {
                    if (node.getId() != null && node.getId().equals("mainContent")) {
                        oldNode = node;
                        break;
                    }
                }
                if (oldNode != null) {
                    Node newNode = loadFXML("fxml/" + String.valueOf(selectedPath) + "View.fxml");
                    System.out.println("newNode = " + newNode);
                    items.set(items.indexOf(oldNode), newNode);
                }

            }
        });

    }

    //click
    @FXML
    public void onClick() {
        System.out.println("MainView.fxml test");
//        System.out.println("splitPane =" + splitPane);
//        System.out.println("navbar =" + navbar);
//        System.out.println("sidebar =" + sidebar);
    }

    @FXML
    private void onClickTestSide() {
        System.out.println("onClickTestSide");
    }

    @FXML
    public void initialize() {
        System.out.println("main initialize");

        //Platform.runLater(() -> { // 在JavaFX线程中延迟执行
//        System.out.println("Platform.runLater");

//        System.out.println("sidebarText = " + sidebarText);
//        System.out.println("splitPane =" + splitPane);
//        System.out.println("navbar =" + navbar);
//        System.out.println("sidebar =" + sidebar);

        splitPane.setDividerPositions(0.2); //将splitPane分隔条位置设置为20%
        loadSidebarTreeView(); //init sidebar
        // });
        ObservableList<Node> items = splitPane.getItems();
        Node oldNode = null;
        for (Node node : items) {
            if (node.getId() != null && node.getId().equals("mainContent")) {
                oldNode = node;
                break;
            }
        }

        if (oldNode != null) {
            Node newNode = loadFXML("fxml/CourseView.fxml");
            System.out.println("newNode = " + newNode);
            items.set(items.indexOf(oldNode), newNode);
        }
    }
}
