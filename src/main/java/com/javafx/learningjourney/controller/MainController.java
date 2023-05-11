package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

import static com.javafx.learningjourney.JavaFXApplication.folderRootPath;

public class MainController {
    private final FileDAO fileDAO = new FileDAOImpl();
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane navbar; //navbar.fxml
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane sidebar; //sidebar.fxml
    /*   @FXML
       private BorderPane courseView; //courseView.fxml*/
    @FXML
    private Button testButton;
    @FXML
    private Text sidebarText;
    //mainText

    /**
     * 加载侧边栏的TreeView
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
        System.out.println("sidebar.getScene() = " + sidebar.lookup("#menuTreeView"));
        TreeView<Path> menuTreeView = (TreeView<Path>) sidebar.lookup("#menuTreeView");
        System.out.println("menuTreeView = " + menuTreeView);

        menuTreeView.setRoot(rootItem); //set the root to the TreeView
        menuTreeView.setShowRoot(false); //不显示根节点

    }

    //click
    @FXML
    public void onClick() {
        System.out.println("onClick");
        System.out.println("splitPane =" + splitPane);
        System.out.println("navbar =" + navbar);
        System.out.println("sidebar =" + sidebar);

    }

    @FXML
    public void initialize() {
        System.out.println("main initialize");

        //Platform.runLater(() -> { // 在JavaFX线程中延迟执行
        System.out.println("Platform.runLater");

        System.out.println("sidebarText = " + sidebarText);

        System.out.println("splitPane =" + splitPane);
        splitPane.setDividerPositions(0.3); //将splitPane分隔条位置设置为30%
        System.out.println("navbar =" + navbar);
        System.out.println("sidebar =" + sidebar);
        loadSidebarTreeView(); //init sidebar
        // });
    }
}
