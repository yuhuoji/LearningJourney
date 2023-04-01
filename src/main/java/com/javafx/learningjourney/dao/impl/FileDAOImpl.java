package com.javafx.learningjourney.dao.impl;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.util.RootPathUtil;

import java.nio.file.Path;

public class FileDAOImpl implements FileDAO {
    private final RootPathUtil rootPathUtil = new RootPathUtil();

    public Path getRootDirectoryPath() {
        return rootPathUtil.getRootPath();
    }
}
