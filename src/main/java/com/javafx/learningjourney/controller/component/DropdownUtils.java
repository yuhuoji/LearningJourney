package com.javafx.learningjourney.controller.component;


import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

/**
 * 下拉框效果
 */
public class DropdownUtils {

    public static void createDropdown(StackPane stackPane, String fxmlPath, Runnable code) {
        try {
            // 加载FXML文件
            FXMLLoader loader = new FXMLLoader(DropdownUtils.class.getResource(fxmlPath));
            Node dropdownContent = loader.load();

            // 设置初始状态
            dropdownContent.setVisible(false);

            // 添加下拉内容到StackPane
            stackPane.getChildren().add(dropdownContent);

            // 添加点击事件处理
            stackPane.setOnMouseClicked(event -> {
                if (dropdownContent.isVisible()) {
                    // 隐藏下拉内容
                    hideDropdown(dropdownContent);
                } else {
                    // 显示下拉内容
                    showDropdown(dropdownContent);
                }
                // 执行自定义代码
                code.run();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showDropdown(Node dropdownContent) {
        dropdownContent.setVisible(true);
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), dropdownContent);
        transition.setFromY(-dropdownContent.getBoundsInParent().getHeight());
        transition.setToY(0);
        transition.play();
    }

    private static void hideDropdown(Node dropdownContent) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), dropdownContent);
        transition.setFromY(0);
        transition.setToY(-dropdownContent.getBoundsInParent().getHeight());
        transition.setOnFinished(event -> dropdownContent.setVisible(false));
        transition.play();
    }
}
