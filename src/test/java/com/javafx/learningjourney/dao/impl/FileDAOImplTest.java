package com.javafx.learningjourney.dao.impl;

import com.javafx.learningjourney.dao.FileDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ！！！运行此测试文件前先修改setUp方法中的testFolderPath为自己电脑上的一个测试文件夹!!!
 * 先运行createMoreFiles和createDirectory，最后运行deleteFileOrFolder
 */
class FileDAOImplTest {

    private static Path testFolderPath;

    private final FileDAO fileDAO = new FileDAOImpl();

    @BeforeAll
    static void setUp() {
        testFolderPath = Paths.get("E:/Workspace"); //测试前把Paths.get中的路径更改为自己电脑上的一个测试文件夹
    }

    @Test
    void getRootDirectoryPath() {

    }

    @Test
    void deleteFileOrFolder() {
        Path deletePath = Paths.get(testFolderPath.toString(), "test");
        FileDAOImpl fileDAO = new FileDAOImpl();
        fileDAO.deleteFileOrFolder(deletePath);
    }

    @Test
    void getAllFiles() {
        Path root = Paths.get(testFolderPath.toString(),"test");
        System.out.println(root.getFileName().toString());
    }

    @Test
    void createDirectory() {
        Path createPath = Paths.get(testFolderPath.toString(), "test/data/test1");

        System.out.println(fileDAO.checkFileOrFolderExistence(createPath));
        System.out.println(fileDAO.createDirectory(createPath, "createTest/aaa/bbb/ccc"));
        System.out.println(fileDAO.checkFileOrFolderExistence(createPath));
    }

    @Test
    void renameFileOrFolder() {
        System.out.println(fileDAO.renameFileOrFolder(Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2", "test2.log"), "test222.log"));
        System.out.println(fileDAO.renameFileOrFolder(Paths.get(testFolderPath.toString(), "test", "data", "test1"), "test111"));
    }

    @Test
    void isFileExist() {
        Path filePath = Paths.get(testFolderPath.toString(), "test", "data");

        System.out.println("root = " + filePath.toAbsolutePath());

        System.out.println(fileDAO.checkFileOrFolderExistence(filePath, "test1"));
        System.out.println(fileDAO.checkFileOrFolderExistence(Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2")));
        System.out.println(fileDAO.checkFileOrFolderExistence(Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2"), "test2.log"));
        System.out.println(fileDAO.checkFileOrFolderExistence(Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2"), "test33.log"));

    }

    @Test
    void moveFileOrFolder() {
        Path path1 = Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2", "test3", "test4", "test5");
        System.out.println("path1 = " + path1);
        System.out.println(fileDAO.moveFileOrFolder(path1, testFolderPath)); //已经存在了，所以返回false

        Path path2 = Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2", "test3", "test3.log");
        Path path3 = Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2", "test2.log");
        System.out.println("path2 = " + path2);
        System.out.println("path3 = " + path3);
        System.out.println(fileDAO.moveFileOrFolder(path2, path3));
    }

    @Test
    void createMoreFiles() {

        try {
            Files.createDirectories(Paths.get(testFolderPath.toString(), "\\test\\data\\test1\\test2\\test3\\test4\\test5\\"));
            Files.write(Paths.get(testFolderPath.toString(), "\\test\\data\\test1\\test2\\test2.log"), "hello".getBytes());
            Files.write(Paths.get(testFolderPath.toString(), "\\test\\data\\test1\\test2\\test3\\test3.log"), "hello".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("success");
    }
}