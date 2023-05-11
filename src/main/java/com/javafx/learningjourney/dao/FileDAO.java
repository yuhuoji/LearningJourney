package com.javafx.learningjourney.dao;

import javafx.scene.control.TreeItem;

import java.nio.file.Path;

public interface FileDAO {
    //CRUD

    //create
    boolean createDirectory(Path root, String directoryName);

    //delete
    boolean deleteFileOrFolder(Path filePath);

    boolean deleteFileOrFolder(Path root, String fileName);

    boolean emptyFolder(Path path);

    //update
    boolean renameFileOrFolder(Path oldNamePath, String newName);

    boolean moveFileOrFolder(Path fromPath, Path toPath);

    //read
    boolean checkFileOrFolderExistence(Path filePath);

    boolean checkFileOrFolderExistence(Path root, String fileName);

    TreeItem<Path> createTreeOfAllFoldersInCurrentLevel(Path root);

    TreeItem<Path> createTreeOfAllFilesInCurrentLevel(Path root);

    TreeItem<Path> createTreeOfAllFilesAndFolders(Path root);
}
