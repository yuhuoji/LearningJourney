package com.javafx.learningjourney.controller.course;

import com.javafx.learningjourney.util.Cache;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AddCourseDialogController {

    @FXML
    private ToggleButton basicButton;

    @FXML
    private ToggleButton courseMaterialsButton;

    @FXML
    private ToggleButton otherButton;

    @FXML
    private Pane contentPane;

    @FXML
    private void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        basicButton.setToggleGroup(toggleGroup);
        courseMaterialsButton.setToggleGroup(toggleGroup);
        otherButton.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                // 当没有按钮被选中时，将所有按钮都设置为未选中状态
                toggleGroup.getToggles().forEach(toggle -> ((ToggleButton) toggle).setSelected(false));
            }
        });

        // 设置ToggleButton按钮的事件处理程序
        basicButton.setOnAction(event -> handleButtonAction(basicButton));
        courseMaterialsButton.setOnAction(event -> handleButtonAction(courseMaterialsButton));
        otherButton.setOnAction(event -> handleButtonAction(otherButton));

        basicButton.setSelected(true); // 默认选中按钮
        handleButtonAction(basicButton); // 默认显示页面

        Cache.put("contentPane", contentPane);
    }

    /**
     * Event handler for the selected button
     *
     * @param button Selected button
     */
    @FXML
    private void handleButtonAction(ToggleButton button) {
        contentPane.getChildren().clear(); // 清除主要内容区域的所有子节点
        if (button.isSelected()) { // 根据按钮的选中状态切换页面内容
            if (button == basicButton) {
                contentPane.getChildren().add(basic());

            } else if (button == courseMaterialsButton) {
                contentPane.getChildren().add(material());
            } else if (button == otherButton) {
                contentPane.getChildren().add(other());
            }
        }
    }

    private Pane basic() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(50));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // 创建标签和输入框
        Label nameLabel = new Label("Course name:");
        TextField nameField = new TextField();

        Label creditLabel = new Label("Credit:");
        TextField creditField = new TextField();

        Label scoreLabel = new Label("Score:");
        TextField scoreField = new TextField();

        Label termLabel = new Label("Term:");
        TextField termField = new TextField();

        Label informationLabel = new Label("Teacher's contact information:");
        TextField informationField = new TextField();

        // 将标签和输入框添加到GridPane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(creditLabel, 0, 1);
        gridPane.add(creditField, 1, 1);
        gridPane.add(scoreLabel, 0, 2);
        gridPane.add(scoreField, 1, 2);
        gridPane.add(termLabel, 0, 3);
        gridPane.add(termField, 1, 3);
        gridPane.add(informationLabel, 0, 4);
        gridPane.add(informationField, 1, 4);

        // 创建按钮
        Button submitButton = new Button("Add");
        Button cancelButton = new Button("Clear");

        // 将按钮添加到GridPane
        gridPane.add(submitButton, 0, 5);
        gridPane.add(cancelButton, 1, 5);

        // 创建包裹GridPane的StackPane，并将GridPane居中对齐
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(gridPane);

        // 设置整体缩放
        stackPane.setScaleX(1.2);
        stackPane.setScaleY(1.2);

        return stackPane;
    }

    private Pane material() {
        Pane page = new Pane();

        // 创建Label并设置文字
        Label label = new Label("Please select or drag your course materials here.");
        label.setLayoutX(10);
        label.setLayoutY(10);
        Label pathLabel = new Label("File path: ");
        pathLabel.setLayoutX(10);
        pathLabel.setLayoutY(30);

        // 创建拖拽框
        Rectangle dragBox = new Rectangle(300, 300);
        dragBox.setFill(Color.TRANSPARENT);
        dragBox.setStroke(Color.BLACK);
        dragBox.setLayoutX(10);
        dragBox.setLayoutY(50);

        // 添加拖拽事件处理程序
        dragBox.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(javafx.scene.input.TransferMode.ANY);
            }
            event.consume();
        });

        dragBox.setOnDragDropped(event -> {
            boolean success = false;
            if (event.getDragboard().hasFiles()) {
                // 获取拖拽的文件或文件夹路径
                String path = event.getDragboard().getFiles().get(0).getAbsolutePath();
                // 在Label上显示路径
                pathLabel.setText("File path: " + path);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        // 将Label和拖拽框添加到Pane中
        page.getChildren().addAll(label, pathLabel, dragBox);



        return page;
    }

    private Pane other() {
        Pane page = new Pane();
        return page;
    }

}
