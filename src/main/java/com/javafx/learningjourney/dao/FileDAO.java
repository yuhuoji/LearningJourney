package com.javafx.learningjourney.dao;

import javafx.scene.control.TreeItem;

import java.nio.file.Path;

public interface FileDAO {
    Path getRootDirectoryPath();

    //CRUD

    //create
    boolean createDirectory(Path root, String directoryName);

    //delete
    boolean deleteFileOrFolder(Path filePath);

    boolean deleteFileOrFolder(Path root, String fileName);

    //update
    boolean renameFileOrFolder(Path oldNamePath, String newName);

    boolean moveFileOrFolder(Path fromPath, Path toPath);

    //read
    boolean checkFileOrFolderExistence(Path filePath);

    boolean checkFileOrFolderExistence(Path root, String fileName);

    <T> TreeItem getAllFiles(Path root);
}
