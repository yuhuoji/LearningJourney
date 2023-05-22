package com.javafx.learningjourney.controller;

import com.javafx.learningjourney.JavaFXApplication;
import com.javafx.learningjourney.controller.component.RectangleItemController;
import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import com.javafx.learningjourney.util.Cache;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.javafx.learningjourney.JavaFXApplication.*;

public class CourseController {
    private final FileDAO fileDAO;
    @FXML
    public BorderPane mainContent;
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
        Cache.put(this.getClass().getSimpleName(), this); //将当前controller的引用放入缓存
        loadCourses();
    }

    /**
     * Load courses and bind click event to navigate to a specific course details page
     */
    private void loadCourses() {
        System.out.println("loadCourses");
        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFoldersInCurrentLevel(Paths.get(((Path) Cache.get("folderRootPath")).toString(), "Course"));

        if (rootItem == null) {
            System.out.println("rootItem is null");
            rootItem = new TreeItem<>(Paths.get(((Path) Cache.get("folderRootPath")).toString(), "Course", "New Course").getFileName()); // 创建根节点
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
                rectangleItemController.setFolderImage(Paths.get(((Path) Cache.get("folderRootPath")).toString(), "Course", folderName).toString()); // FIXME 设置图片

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

                    Cache.put("currentCourse", labelName);

//                    System.out.println("currentPath: " + Cache.get("currentPath"));
                    Path newPath = Paths.get(((Path) (Cache.get("currentPath"))).toString(), labelName);
//                    System.out.println("newPath: " + newPath);
                    Cache.put("currentPath", newPath); //update current path

                    Node newNode = loadFXML("fxml/information/CourseInformation.fxml");
                    Pane root = (Pane) Cache.get("mainContent");
                    root.getChildren().clear();
                    root.getChildren().add(newNode);
                });

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("load Courses failed");
            }

        }

        //System.out.println(" " + courseViewGridPane.getRowConstraints() + " " + courseViewGridPane.getColumnConstraints());
    }

    @FXML
    public void onClickStatistics() {
        System.out.println("onClickAnalyze");
        // 创建一个新的舞台用于弹窗
        Stage dialogStage = new Stage();
        Cache.put("dialogStage", dialogStage);
        Node dialogRoot = loadFXML("fxml/dialog/statisticDialog.fxml");
        Scene dialogScene = new Scene((Parent) dialogRoot);
        dialogStage.setScene(dialogScene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.showAndWait();
    }

    @FXML
    public void onClickAdd() {
        System.out.println("onClickAdd");

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Add a course");
        alert.setHeaderText("Add a course");
        alert.setContentText("Add a course");
        alert.showAndWait();
    }

}
