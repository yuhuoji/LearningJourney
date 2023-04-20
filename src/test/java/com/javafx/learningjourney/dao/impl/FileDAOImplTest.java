package com.javafx.learningjourney.dao.impl;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.util.RootPathUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 测试文件夹在target下的LearningJourneyFiles下
 */
class FileDAOImplTest {

    private static Path testFolderPath;

    private final FileDAO fileDAO = new FileDAOImpl();

    @BeforeAll
    static void setUp() {
        testFolderPath = RootPathUtil.getFolderRootPath();
    }

    //可以先运行这个方法，再测试其他方法
    @Test
    void createMoreFiles() {
        fileDAO.createDirectory(testFolderPath,"course");
        fileDAO.createDirectory(testFolderPath,"Internship");
        fileDAO.createDirectory(testFolderPath,"Research");
        fileDAO.createDirectory(testFolderPath,"Work");

//        try {
//            Files.createDirectories(Paths.get(testFolderPath.toString(), "\\test\\data\\test1\\test2\\test3\\test4\\test5\\"));
//            Files.write(Paths.get(testFolderPath.toString(), "\\test\\data\\test1\\test2\\test2.log"), "hello".getBytes());
//            Files.write(Paths.get(testFolderPath.toString(), "\\test\\data\\test1\\test2\\test3\\test3.log"), "hello".getBytes());
//            Files.write(Paths.get(testFolderPath.toString(), "test\\test01.log"), "test01".getBytes());
//            Files.write(Paths.get(testFolderPath.toString(), "test\\test02.log"), "test02".getBytes());
//            Files.write(Paths.get(testFolderPath.toString(), "test\\test03.log"), "test03".getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }

        System.out.println("created success");
    }

    @Test
    void emptyFolder() {
        Path emptyPath = Paths.get(testFolderPath.toString());
        fileDAO.emptyFolder(emptyPath);
    }

    //最后可以运行这个方法清空文件夹
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
    void createTreeOfAllFilesInCurrentLevel() {
        Path path = Paths.get(testFolderPath.toString(), "test");
        System.out.println(fileDAO.createTreeOfAllFilesInCurrentLevel(path));
    }

    @Test
    void renameFileOrFolder() {
        Path path1 = Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2", "test2.log");
        System.out.println(path1);
        System.out.println(fileDAO.renameFileOrFolder(path1, "test222.log"));

        Path path2 = Paths.get(testFolderPath.toString(), "test", "data", "test1");
        System.out.println(path2);
        System.out.println(fileDAO.renameFileOrFolder(path2, "test111"));
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
    void getRootDirectoryPath() {

        System.out.println(RootPathUtil.getRootPath());
        System.out.println(RootPathUtil.getFolderRootPath());

    }

}