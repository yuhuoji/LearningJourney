package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.JavaFXApplication;
import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class MainController {
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
        //System.out.println("sidebar.getScene() = " + sidebar.lookup("#menuTreeView"));
        TreeView<Path> menuTreeView = (TreeView<Path>) sidebar.lookup("#menuTreeView");
        //System.out.println("menuTreeView = " + menuTreeView);

        menuTreeView.setRoot(rootItem); //set the root to the TreeView
        menuTreeView.setShowRoot(false); //不显示根节点

        menuTreeView.setOnMouseClicked(event -> { // 处理侧栏点击事件的逻辑
            TreeItem<Path> selectedItem = menuTreeView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Path selectedPath = selectedItem.getValue();
                switchToPage("fxml/"+String.valueOf(selectedPath)+"View.fxml",this.mainContent); //TODO
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


    /**
     * TODO
     * 切换页面
     * @param fxmlPath 选中的节点名称
     */
    public static void switchToPage(String fxmlPath, Pane mainContent) {
        System.out.println("switchToPage fxmlPath = " + fxmlPath);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            System.out.println(JavaFXApplication.class.getClassLoader().getResource(fxmlPath));
            fxmlLoader.setLocation(Objects.requireNonNull(JavaFXApplication.class.getClassLoader().getResource(fxmlPath)));

            System.out.println("fxmlLoader.getController() = " + fxmlLoader.getController());

            Parent page = fxmlLoader.load();
            mainContent.getChildren().setAll(page);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        switchToPage("fxml/CourseView.fxml",this.mainContent);


    }
}
