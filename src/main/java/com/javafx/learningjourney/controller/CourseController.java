package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.JavaFXApplication;
import com.javafx.learningjourney.controller.component.RectangleItemController;
import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import com.javafx.learningjourney.util.CustomEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.javafx.learningjourney.JavaFXApplication.*;

/**
 * course and internship controller
 */
public class CourseController {
    public static String currentCourse = "Course";
    private final FileDAO fileDAO;
    @FXML
    public BorderPane mainContent;
    private EventHandler<CustomEvent> eventHandler; // 事件处理器
    @FXML
    private ImageView courseViewSearchImage;
    @FXML
    private TextField courseViewSearchTextField;
    @FXML
    private Button courseViewSearchButton;
    @FXML
    private Button courseViewAddButton;
    @FXML
    private ScrollPane courseViewScrollPane;
    @FXML
    private GridPane courseViewGridPane;
    @FXML
    private Button courseViewTestButton;

    public CourseController() {
        fileDAO = new FileDAOImpl();
    }


    //初始化数据
    @FXML
    public void initialize() {
        System.out.println("course initialize");
        loadCourses();
    }

    /**
     * 加载课程并绑定点击跳转课程详情页面的方法
     */
    private void loadCourses() {
        System.out.println("loadCourses");
        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFoldersInCurrentLevel(Paths.get(folderRootPath.toString(), "Course"));

        if (rootItem == null) {
            System.out.println("rootItem is null");
            rootItem = new TreeItem<>(Paths.get(folderRootPath.toString(), "Course", "New Course").getFileName()); // 创建根节点
        }
        int row = 0;
        int column = 0;
        int columnsPerPage = 2; // 每页显示的列数
        double spacing = 20; // 间距

        for (TreeItem<Path> child : rootItem.getChildren()) { //遍历子节点
            String folderName = child.getValue().toString();
            System.out.println("Folder Name: " + folderName);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(JavaFXApplication.class.getClassLoader().getResource("fxml/component/RectangleItem.fxml"));
                Node rectangleItem = fxmlLoader.load();
                RectangleItemController rectangleItemController = fxmlLoader.getController();
                rectangleItemController.setLabel(folderName);
                rectangleItemController.setFolderImage(Paths.get(folderRootPath.toString(), "Course", folderName).toString()); // FIXME 设置图片

                GridPane.setRowIndex(rectangleItem, row);
                GridPane.setColumnIndex(rectangleItem, column);
                courseViewGridPane.getChildren().add(rectangleItem);

                // Increment row and column to position the next RectangleItem
                column++;
                if (column >= columnsPerPage) { // 超过每页显示的列数，换行
                    column = 0;
                    row++;
                    RowConstraints rowConstraints = new RowConstraints(); // 设置行的高度
                    rowConstraints.setPrefHeight(100); // 设置长方形的高度
                    courseViewGridPane.getRowConstraints().add(rowConstraints); // 设置行的高度
                }

          /*      // Set column and row constraints
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPercentWidth(100.0 / columnsPerPage); // 设置长方形的宽度
                courseViewGridPane.getColumnConstraints().add(columnConstraints); // 设置列的宽度
*/

                // Add spacing between rectangles
                courseViewGridPane.setHgap(spacing);
                courseViewGridPane.setVgap(spacing);

                rectangleItem.setOnMouseClicked(event -> { //设置点击事件，获取点击的label
                    String labelName = rectangleItemController.getLabelName();
                    System.out.println("Clicked: " + labelName);

                    currentCourse = labelName; //设置当前课程

                    Node newNode = loadFXML("fxml/information/CourseInformation.fxml");
                    System.out.println(mainContent.getScene());
                    System.out.println(mainContent.getScene().getRoot());

                    MainController mainController = (MainController) controllers.get(MainController.class.getSimpleName()); //获取MainController的引用
                    mainController.replaceMainContent(newNode); //跳转页面
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //System.out.println(" " + courseViewGridPane.getRowConstraints() + " " + courseViewGridPane.getColumnConstraints());
    }

    @FXML
    public void onClickTest() {
        System.out.println("onClickTest");
    }

    @FXML
    public void onClickAnalyze() {
        System.out.println("onClickAnalyze");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText("统计信息");
        alert.setContentText("这是弹窗的内容");
        alert.showAndWait();
    }

    @FXML
    public void onClickAdd() {
        System.out.println("onClickAdd");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText("这是弹窗的标题");
        alert.setContentText("这是弹窗的内容");
        alert.showAndWait();
    }

}
