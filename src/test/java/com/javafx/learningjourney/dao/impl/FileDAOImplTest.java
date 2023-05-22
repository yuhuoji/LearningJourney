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
    void testCreateMoreFiles() {
        fileDAO.createDirectory(testFolderPath,"Course");
        fileDAO.createDirectory(testFolderPath,"Internship");
        fileDAO.createDirectory(testFolderPath,"Research");
        fileDAO.createDirectory(testFolderPath,"Work");
        try {
            Files.createDirectories(Paths.get(testFolderPath.toString(), "Course\\Digital Circuits\\Learning materials"));
            Files.createDirectories(Paths.get(testFolderPath.toString(), "Course\\Digital Circuits\\Course notes"));
            Files.createDirectories(Paths.get(testFolderPath.toString(), "Course\\Digital Circuits\\Course work"));
            Files.createDirectories(Paths.get(testFolderPath.toString(), "Course\\Digital Circuits\\Experimental project"));
            Files.createDirectories(Paths.get(testFolderPath.toString(), "Course\\Cybersecurity Law"));
            Files.createDirectories(Paths.get(testFolderPath.toString(), "Course\\Intellectual Property Law"));
            Files.createDirectories(Paths.get(testFolderPath.toString(), "Course\\Software Engineering"));
            Files.createDirectories(Paths.get(testFolderPath.toString(), "Course\\Logistics and Supply Chain Management"));
            Files.write(Paths.get(testFolderPath.toString(), "Course\\Digital Circuits\\Digital Circuits.json"), "Digital Circuits".getBytes());
            Files.write(Paths.get(testFolderPath.toString(), "Course\\Digital Circuits\\Course notes\\Course notes.txt"), "Course notes".getBytes());
            Files.write(Paths.get(testFolderPath.toString(), "Course\\Digital Circuits\\Course work\\Course work.txt"), "Course work".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("created success");
    }

    @Test
    void testEmptyFolder() {
        Path emptyPath = Paths.get(testFolderPath.toString());
        fileDAO.emptyFolder(emptyPath);
    }

    //最后可以运行这个方法清空文件夹
    @Test
    void testDeleteFileOrFolder() {
        Path deletePath = Paths.get(testFolderPath.toString(), "test");
        FileDAOImpl fileDAO = new FileDAOImpl();
        fileDAO.deleteFileOrFolder(deletePath);
    }

    @Test
    void testGetAllFiles() {

        Path root = Paths.get(testFolderPath.toString(),"test");
        System.out.println(root.getFileName().toString());

    }

    @Test
    void testCreateDirectory() {
        Path createPath = Paths.get(testFolderPath.toString(), "test/data/test1");

        System.out.println(fileDAO.checkFileOrFolderExistence(createPath));
        System.out.println(fileDAO.createDirectory(createPath, "createTest/aaa/bbb/ccc"));
        System.out.println(fileDAO.checkFileOrFolderExistence(createPath));
    }

    @Test
    void testCreateTreeOfAllFilesInCurrentLevel() {
        Path path = Paths.get(testFolderPath.toString(), "test");
        System.out.println(fileDAO.createTreeOfAllFilesInCurrentLevel(path));
    }

    @Test
    void testRenameFileOrFolder() {
        Path path1 = Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2", "test2.log");
        System.out.println(path1);
        System.out.println(fileDAO.renameFileOrFolder(path1, "test222.log"));

        Path path2 = Paths.get(testFolderPath.toString(), "test", "data", "test1");
        System.out.println(path2);
        System.out.println(fileDAO.renameFileOrFolder(path2, "test111"));
    }

    @Test
    void testIsFileExist() {
        Path filePath = Paths.get(testFolderPath.toString(), "test", "data");

        System.out.println("root = " + filePath.toAbsolutePath());

        System.out.println(fileDAO.checkFileOrFolderExistence(filePath, "test1"));
        System.out.println(fileDAO.checkFileOrFolderExistence(Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2")));
        System.out.println(fileDAO.checkFileOrFolderExistence(Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2"), "test2.log"));
        System.out.println(fileDAO.checkFileOrFolderExistence(Paths.get(testFolderPath.toString(), "test", "data", "test1", "test2"), "test33.log"));

    }

    @Test
    void testMoveFileOrFolder() {
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
    void testGetRootDirectoryPath() {

        System.out.println(RootPathUtil.getRootPath());
        System.out.println(RootPathUtil.getFolderRootPath());

    }

}