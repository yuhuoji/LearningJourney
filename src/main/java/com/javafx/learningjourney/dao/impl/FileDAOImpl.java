package com.javafx.learningjourney.dao.impl;

import com.javafx.learningjourney.dao.FileDAO;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class FileDAOImpl implements FileDAO {

    /**
     * 递归遍历指定路径当前层级下的所有文件和文件夹，并将它们作为TreeItem的子节点添加，最后返回TreeItem的树形结构。
     * dfs
     * (除了json文件)
     *
     * @param path 树形结构的根路径，可以是文件或者文件夹。
     * @return 根据指定路径创建的TreeItem对象，其中包含了整个目录树的结构。
     */
    @Override
    public TreeItem<Path> createTreeOfAllFilesAndFolders(Path path) {
        if (!Files.exists(path)) { // 如果path代表的文件或文件夹不存在，则直接返回
            return null;
        }

        if (!Files.isDirectory(path)) { // 如果path代表的不是文件夹，则直接返回
            return null;
        }

        TreeItem<Path> root = new TreeItem<>(path.getFileName()); // 创建根节点

        try {
            if (Objects.requireNonNull((new File(path.toString())).listFiles()).length == 0) { //path代表空文件夹则直接返回
                return null;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }

        try (Stream<Path> list = Files.list(path)) {
            list.forEach(p -> { // 遍历path下的所有文件和文件夹
                TreeItem<Path> item = new TreeItem<>(p.getFileName());
                if (Files.isDirectory(p)) { // 如果是文件夹，则递归调用createTree()方法，并将返回值添加到子节点中。
                    item.getChildren().add(createTreeOfAllFilesAndFolders(p));
                }
                root.getChildren().add(item); // 将子节点添加到根节点下
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }

    /**
     * 遍历指定路径当前层级下的所有文件夹，并将它们作为TreeItem的子节点添加，最后返回TreeItem的树形结构。
     *
     * @param path 树形结构的根路径，必须是文件夹。
     * @return 返回指定路径下的所有文件夹
     */
    @Override
    public TreeItem<Path> createTreeOfAllFoldersInCurrentLevel(Path path) {
        if (!Files.exists(path)) { // 如果path代表的文件或文件夹不存在，则直接返回
            return null;
        }

        if (!Files.isDirectory(path)) { // 如果path代表的不是文件夹，则直接返回
            return null;
        }

        TreeItem<Path> root = new TreeItem<>(path.getFileName()); // 创建根节点

        //遍历当前path路径下的所有文件夹并添加给root
        try (Stream<Path> list = Files.list(path)) {
            list.forEach(p -> { // 遍历path下的所有文件和文件夹
                if (Files.isDirectory(p)) { // 如果是文件夹，则添加到子节点中
                    TreeItem<Path> item = new TreeItem<>(p.getFileName());
                    root.getChildren().add(item);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }

    /**
     * 遍历指定路径当前层级下的所有文件(除了json文件)，并将它们作为TreeItem的子节点添加，最后返回TreeItem的树形结构。
     *
     * @param path 树形结构的根路径，必须是文件夹。
     * @return 返回指定路径下的所有文件(除了json文件)
     */
    @Override
    public TreeItem<Path> createTreeOfAllFilesInCurrentLevel(Path path) {
        if (!Files.exists(path)) { // 如果path代表的文件或文件夹不存在，则直接返回
            return null;
        }

        if (!Files.isDirectory(path)) { // 如果path代表的不是文件夹，则直接返回
            return null;
        }

        TreeItem<Path> root = new TreeItem<>(path.getFileName()); // 创建根节点

        //遍历当前path路径下的所有文件并添加给root
        try (Stream<Path> list = Files.list(path)) {
            list.forEach(p -> { // 遍历path下的所有文件和文件夹
                // 如果是文件且不是json文件，则添加到子节点中
                if (Files.isDirectory(p) || (!Files.isDirectory(p) && !p.getFileName().toString().endsWith(".json"))) {
                    System.out.println(p);
                    TreeItem<Path> item = new TreeItem<>(p.getFileName());
                    root.getChildren().add(item);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }

    /**
     * TODO 正则检查名称是否合法
     * 检查指定目录下是否存在文件夹，否则在指定目录下创建指定文件或文件夹(单级目录)
     * TODO 创建多级目录
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

        try {
            Files.createDirectories(filePath); //新建名字为directoryName的文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 检查指定目录下是否存在指定文件或文件夹
     *
     * @param root 指定目录
     * @param name 文件或文件夹名
     * @return 是否存在
     */
    @Override
    public boolean checkFileOrFolderExistence(Path root, String name) {
        Path filePath = Paths.get(root.toString(), name);
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

        try (Stream<Path> walk = Files.walk(filePath)) { //遍历filePath下所有文件，访问方式是dfs深度搜索
            walk.sorted(Comparator.reverseOrder()) //倒序遍历
                    .forEach(pathItem -> {
                        try {
                            Files.delete(pathItem); //删除文件
//                            System.out.println("delete file success : " + pathItem);
                        } catch (IOException e) {
                            e.printStackTrace();
//                            System.out.println("delete file failed : " + pathItem);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("delete file failed : " + filePath);
            return false;
        }

//        System.out.println("delete success");
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

    /**
     * 清空指定文件夹下的所有文件，但不删除该文件夹
     *
     * @param path 指定文件夹的路径
     * @return 是否清空成功
     */
    @Override
    public boolean emptyFolder(Path path) {
        if (!Files.exists(path)) { //如果该path不存在则直接返回
            return false;
        }

        if (!Files.isDirectory(path)) { //如果该path不是文件夹则直接返回
            return false;
        }

        File directory = new File(path.toString());

        if (Objects.requireNonNull(directory.list()).length == 0) { //如果该path是空文件夹则直接返回
            return true;
        }

        //将该文件夹清空，但不删除该文件夹
        try (Stream<Path> walk = Files.walk(path)) { //遍历path下所有文件，访问方式是dfs深度搜索
            walk.filter(p -> !p.equals(path)) //不删除根文件夹
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
            System.out.println("delete file failed : " + path);
            return false;
        }

        return true;
    }

    /**
     * 重命名文件或文件夹
     *
     * @param oldNamePath 文件或文件夹的完整路径
     * @param newName     新的文件或文件夹的名字
     * @return 是否重命名成功
     */
    @Override
    public boolean renameFileOrFolder(Path oldNamePath, String newName) {
        if (!Files.exists(oldNamePath)) { //如果原来的文件或文件夹不存在则直接返回false
            return false;
        }

        if (oldNamePath.getFileName().toString().equals(newName)) { //如果原来的名字和修改的新名字相同则直接返回false
            return false;
        }

        try {
            Path newNamePath = Paths.get(oldNamePath.getParent().toString(), newName);
            Files.move(oldNamePath, newNamePath); //原地移动实现改名
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

        try {
            Files.move(fromPath, toPath.resolve(fromPath.getFileName())); //fromPath为需要移动的文件夹或文件的路径，toPath为目标文件夹的路径，进行移动
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
