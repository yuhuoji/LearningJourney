package com.javafx.learningjourney.dao.impl;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.util.RootPathUtil;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDAOImpl implements FileDAO {
    private final RootPathUtil rootPathUtil = new RootPathUtil();

    public Path getRootDirectoryPath() {
        return rootPathUtil.getRootPath();
    }

    public void getAllFiles(Path root){
        String dirPath = "D:\\"; // 目标目录路径

        File dir = new File(dirPath);
        File[] files = dir.listFiles(); // 获取目录下所有文件及子目录

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) { // 如果是文件则输出文件名
                    System.out.println(file.getName());
                } else if (file.isDirectory()) { // 如果是目录则递归调用该方法
                    System.out.println("[" + file.getName() + "]");
                    getAllFiles(Paths.get(file.getAbsolutePath())); // 递归调用该方法
                }
            }
        }

    }

    //查看项目根目录的同级是否有名字为“LearningJourneyFiles”的文件夹，如果没有则创建

}
