package com.javafx.learningjourney.dao.impl;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.util.RootPathUtil;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class FileDAOImpl implements FileDAO {
    private final RootPathUtil rootPathUtil;

    public FileDAOImpl() {
        rootPathUtil = new RootPathUtil();
    }

    /**
     * 获取项目根目录的绝对路径
     *
     * @return 项目根目录的绝对路径
     */
    @Override
    public Path getRootDirectoryPath() {
        return rootPathUtil.getRootPath();
    }


    /**
     * TODO
     * 获取指定目录下的所有文件
     *
     * @param root 指定目录
     * @return 返回树的根节点
     */
    @Override
    public TreeItem getAllFiles(Path root) {
        //创建根节点
        TreeItem<String> rootItem = new TreeItem<>("文件夹");

        //设置根节点展开状态
        rootItem.setExpanded(true);

        //获取指定文件夹路径
        File directory = new File(root.toString());

        //遍历指定路径root下所有文件和文件夹并添加给根节点rootItem
        for (File file : directory.listFiles()) {
            TreeItem<String> item = new TreeItem<>(file.getName());
            item.setValue(file.getName() + "          " + file.getPath()); //设置子节点文本值，包括文件名和路径
            rootItem.getChildren().add(item);
        }

        return rootItem;
    }

    /**
     * TODO 正则检查名称是否合法
     * 检查指定目录下是否存在文件夹，否则在指定目录下创建指定文件或文件夹(单级目录)
     *
     * @param root          指定目录
     * @param directoryName 文件夹名
     * @return 是否创建成功
     */
    @Override
    public boolean createDirectory(Path root, String directoryName) {
        if (directoryName == null || directoryName.trim().equals("")) { //检查是否是正常的文件夹名称
            return false;
        }

        Path filePath = Paths.get(root.toString(), directoryName);
        if (Files.exists(filePath)) { //已经存在该文件夹
            System.out.println(directoryName + "already exists");
            return false;
        }

        //新建名字为fileName的文件夹
        try {
            Files.createDirectories(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 检查指定目录下是否存在指定文件或文件夹
     *
     * @param root     指定目录
     * @param fileName 文件或文件夹名
     * @return 是否存在
     */
    @Override
    public boolean checkFileOrFolderExistence(Path root, String fileName) {
        Path filePath = Paths.get(root.toString(), fileName);
        return checkFileOrFolderExistence(filePath);
    }

    /**
     * 给一个文件的绝对路径，检查该文件是否存在
     *
     * @param filePath 文件的绝对路径
     * @return 是否存在
     */
    @Override
    public boolean checkFileOrFolderExistence(Path filePath) {
        return Files.exists(filePath);
    }

    /**
     * 删除指定文件或文件夹
     *
     * @param filePath 指定需要删除的文件或文件夹的路径
     * @return 是否删除成功
     */
    @Override
    public boolean deleteFileOrFolder(Path filePath) {
        if (!checkFileOrFolderExistence(filePath)) { //不存在该路径，无法删除
            return false;
        }

        try {
            Files.walk(filePath) //遍历filePath下所有文件，访问方式是dfs深度搜索
                    .sorted(Comparator.reverseOrder()) //倒序遍历
                    .forEach(pathItem -> {
                        try {
                            Files.delete(pathItem); //删除文件
                            System.out.println("delete file success : " + pathItem);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("delete file failed : " + pathItem);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("delete file failed : " + filePath);
            return false;
        }
        System.out.println("delete success");
        return true;
    }


    /**
     * 删除指定目录下的指定文件或文件夹
     *
     * @param root     指定目录的路径
     * @param fileName 指定目录的路径下的文件或文件夹名
     * @return 是否删除成功
     */
    @Override
    public boolean deleteFileOrFolder(Path root, String fileName) {

        if (!checkFileOrFolderExistence(root, fileName)) { //检查目录root下是否含有名为fileName的文件或文件夹
            return false;
        }
        Path deletePath = Paths.get(root.toString(), fileName);
        return deleteFileOrFolder(deletePath);
    }

    @Override
    public boolean renameFileOrFolder(Path oldNamePath, String newName) {

        if (oldNamePath.getFileName().toString().equals(newName)) { //如果原来的名字和修改的新名字相同则直接返回false
            return false;
        }

        try {
            Path newNamePath = Paths.get(oldNamePath.getParent().toString(), newName);
            Files.move(oldNamePath, newNamePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 移动文件或文件夹
     *
     * @param fromPath 需要移动的文件和文件夹的路径
     * @param toPath   目的路径
     * @return 是否移动成功
     */
    @Override
    public boolean moveFileOrFolder(Path fromPath, Path toPath) {

        if (!Files.isDirectory(toPath)) { //如果toPath为一个文件的路径而不是文件夹路径则直接返回false
            return false;
        }


        if (toPath.toString().contains(fromPath.toString())) { //如果fromPath已经存在于toPath中则直接返回false，?父亲移动到儿子里?
            return false;
        }


        if (Files.exists(toPath.resolve(fromPath.getFileName()))) { //如果toPath存在和fromPath相同名字的文件或文件夹则直接返回false
            return false;
        }

        //fromPath为需要移动的文件夹或文件的路径，toPath为目标文件夹的路径，进行移动
        try {
            Files.move(fromPath, toPath.resolve(fromPath.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
