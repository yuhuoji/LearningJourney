package com.javafx.learningjourney.controller.dialog;

import com.javafx.learningjourney.entity.Course;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class statisticDialog {
    @FXML
    private ToggleButton statisticButton;

    @FXML
    private ToggleButton curveButton;

    @FXML
    private ToggleButton informationButton;

    @FXML
    private Pane contentPane; //主要内容区域

    @FXML
    private void initialize() {
        // 设置ToggleButton按钮的事件处理程序
        statisticButton.setOnAction(event -> handleButtonAction(statisticButton));
        curveButton.setOnAction(event -> handleButtonAction(curveButton));
        informationButton.setOnAction(event -> handleButtonAction(informationButton));

    }

    @FXML
    private void handleButtonAction(ToggleButton button) {
        if (button.isSelected()) { // 根据按钮的选中状态切换页面内容
            if (button == statisticButton) {
                statistic();
            } else if (button == curveButton) {
                curve();
            } else if (button == informationButton) {
                information();
            }
        }
    }

    /**
     * 成绩统计
     */
    private void statistic() {
         TableView<Course> tableView;
         ObservableList<Course> courseList;

    }

    /**
     * 成绩曲线
     */
    private void curve() {
//        Button loadButton = new Button("加载CSV");
//        loadButton.setOnAction(event -> loadCSVFile());
//
//        Button saveButton = new Button("保存");
//        saveButton.setOnAction(event -> saveCSVFile());
//
//        VBox vbox = new VBox(loadButton, saveButton, tableView);

        //TODO @date 2023-05-18 加载表格
    }

    /**
     * 统计信息
     */
    private void information() {

    }


}
