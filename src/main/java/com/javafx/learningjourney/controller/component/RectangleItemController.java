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
     * Set the label name.
     * @param name label name
     */
    public void setLabel(String name) {
        rectangleItemNameLabel.setText(name);
    }

    /**
     * Set the image.
     * @param imagePath image path
     */
    private void setImage(String imagePath) {
        Image image = new Image(imagePath);
        rectangleItemImageView.setImage(image);
    }

    /**
     * Set the image in the folder with the suffix of .png.
     * @param folderPath folder path
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

    /**
     * Get the label name.
     * @return label name
     */
    public String getLabelName() {
        return rectangleItemNameLabel.getText();
    }
}
