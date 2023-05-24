package com.javafx.learningjourney.controller.component;

import com.javafx.learningjourney.util.Cache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class NavbarController {
    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem openMenuItem;

    @FXML
    private MenuItem exportMenuItem;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private void initialize() {

    }

    /**
     * Open the folder
     *
     * @param actionEvent click event
     */
    @FXML
    public void handleOpen(ActionEvent actionEvent) {
        System.out.println("openMenuItem clicked");
        openFolder(Cache.get("folderRootPath").toString());
    }


    /**
     * Export the folder
     *
     * @param actionEvent click event
     */
    @FXML
    public void handleExport(ActionEvent actionEvent) {
        // 显示文件夹选择对话框
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(Cache.get("rootDirectoryPath").toString()));
        File selectedDirectory = directoryChooser.showDialog((Stage) Cache.get("stage"));


        if (selectedDirectory != null) {
            // 获取选择的文件夹路径
            String folderPath = selectedDirectory.getAbsolutePath();

            // 显示文件保存对话框以选择目标ZIP文件路径
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the destination ZIP file path");
            fileChooser.setInitialFileName("archive.zip");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("ZIP file (*.zip)", "*.zip");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File selectedFile = fileChooser.showSaveDialog((Stage) Cache.get("stage"));

            if (selectedFile != null) {
                String zipFilePath = selectedFile.getAbsolutePath();

                // 压缩文件夹
                compressFolder(folderPath, zipFilePath);

                showAlert(AlertType.INFORMATION, "Success", "The folder has been successfully compressed to a ZIP file:" + zipFilePath);
            } else {
                showAlert(AlertType.WARNING, "Warning", "The destination ZIP file path is not selected.");
            }
        }
    }

    /**
     * Open the help file
     *
     * @param actionEvent click event
     */
    @FXML
    public void handleHelp(ActionEvent actionEvent) {
        String currentDirectory = Cache.get("rootDirectoryPath").toString();
        Path readmePath = Paths.get(currentDirectory).getParent().resolve("README.md");
        openFile(readmePath.toFile());
    }

    /**
     * Open the link
     *
     * @param actionEvent click event
     */
    @FXML
    public void handleAbout(ActionEvent actionEvent) {
        openURL("https://github.com/yuhuoji/LearningJourney");
    }

    private void openFolder(String folderPath) {
        try {
            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                Desktop.getDesktop().open(folder);
            } else {
                System.out.println("The specified file does not exist or is not a valid file.");
            }
        } catch (IOException e) {
            System.out.println("Unable to open file:" + e.getMessage());
        }
    }

    private void openFile(File file) {
        try {
            if (file.exists() && file.isFile()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("The specified file does not exist or is not a valid file.");
            }
        } catch (IOException e) {
            System.out.println("Unable to open file: " + e.getMessage());
        }
    }

    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void compressFolder(String folderPath, String zipFilePath) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            File folder = new File(folderPath);
            compressFolderRecursive(folder, folder.getName(), zipOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void compressFolderRecursive(File file, String parentFolder, ZipOutputStream zipOut) throws IOException {
        if (file.isHidden()) {
            return;
        }

        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (File child : children) {
                compressFolderRecursive(child, parentFolder + "/" + file.getName(), zipOut);
            }
        } else {
            byte[] buffer = new byte[1024];

            try (FileInputStream fis = new FileInputStream(file)) {
                ZipEntry zipEntry = new ZipEntry(parentFolder + "/" + file.getName());
                zipOut.putNextEntry(zipEntry);

                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zipOut.write(buffer, 0, length);
                }

                zipOut.closeEntry();
            }
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
