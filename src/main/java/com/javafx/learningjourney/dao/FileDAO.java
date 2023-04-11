package com.javafx.learningjourney.dao;

import java.nio.file.Path;

public interface FileDAO {
    Path getRootDirectoryPath();
    void getAllFiles(Path root);
}
