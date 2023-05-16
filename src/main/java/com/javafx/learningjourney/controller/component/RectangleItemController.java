package com.javafx.learningjourney.controller.component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;

public class RectangleItemController {
    @FXML
    public StackPane rectangleItemStackPane;
    @FXML
    private ImageView rectangleItemImageView;
    @FXML
    private Label rectangleItemNameLabel;

    /**
     *
     * @param name 标签名
     */
    public void setLabel(String name) {
        rectangleItemNameLabel.setText(name);
    }

    /**
     * @param imagePath 图片路径
     */
    private void setImage(String imagePath) {
        Image image = new Image(imagePath);
        rectangleItemImageView.setImage(image);
    }

    /**
     * 根据文件夹随机设置图片
     * @param folderPath 文件夹路径
     */
    public void setFolderImage(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".png")) {
                    String imagePath = file.toURI().toString();
                    setImage(imagePath);
                    break;
                }
            }
        }
    }

    public String getLabelName() {
        return rectangleItemNameLabel.getText();
    }
}
