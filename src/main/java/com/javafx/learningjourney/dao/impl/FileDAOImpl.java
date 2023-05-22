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
     * Recursively traverses all files and folders in the current level of the specified path, adds them as child nodes to TreeItem, and returns the tree structure of TreeItem.
     * Uses depth-first search (DFS) algorithm.
     * (Excludes JSON files)
     *
     * @param path Root path of the tree structure, can be a file or folder.
     * @return TreeItem object created based on the specified path, containing the entire directory tree structure.
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
                if (Files.isDirectory(p)) { // 如果是文件夹，则递归调用方法，并将返回值添加到子节点中。
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
     * Traverses all folders in the current level of the specified path, adds them as child nodes to TreeItem, and returns the tree structure of TreeItem.
     *
     * @param path Root path of the tree structure, must be a folder.
     * @return All folders under the specified path.
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
     * Traverses all files (excluding JSON files) in the current level of the specified path, adds them as child nodes to TreeItem, and returns the tree structure of TreeItem.
     *
     * @param path Root path of the tree structure, must be a folder.
     * @return All files (excluding JSON files) under the specified path.
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
                    //     System.out.println(p);
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
     * Checks if a folder exists in the specified directory, otherwise creates a specified file or folder (single-level directory) in the specified directory.
     *
     * @param root          Specified directory.
     * @param directoryName Folder name.
     * @return Whether the creation is successful.
     */
    //TODO 正则检查名称是否合法
    //TODO 创建多级目录
    @Override
    public boolean createDirectory(Path root, String directoryName) {
        if (directoryName == null || directoryName.trim().equals("")) { //检查是否是正常的文件夹名称
            return false;
        }

        Path filePath = Paths.get(root.toString(), directoryName);
        if (Files.exists(filePath)) { //已经存在该文件夹
//            System.out.println(directoryName + " already exists");
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
     * Checks if a specified file or folder exists in the specified directory.
     *
     * @param root Specified directory.
     * @param name File or folder name.
     * @return Whether it exists.
     */
    @Override
    public boolean checkFileOrFolderExistence(Path root, String name) {
        Path filePath = Paths.get(root.toString(), name);
        return checkFileOrFolderExistence(filePath);
    }

    /**
     * Given the absolute path of a file, checks if the file exists.
     *
     * @param filePath Absolute path of the file.
     * @return Whether the file exists.
     */
    @Override
    public boolean checkFileOrFolderExistence(Path filePath) {
        return Files.exists(filePath);
    }

    /**
     * Deletes the specified file or folder.
     *
     * @param filePath Path of the file or folder to be deleted.
     * @return Whether the deletion is successful.
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
     * Deletes the specified file or folder in the specified directory.
     *
     * @param root     Path of the specified directory.
     * @param fileName Name of the file or folder in the specified directory.
     * @return Whether the deletion is successful.
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
     * Clears all files in the specified folder, without deleting the folder itself.
     *
     * @param path Path of the specified folder.
     * @return Whether the clearing is successful.
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
     * Renames a file or folder.
     *
     * @param oldNamePath Full path of the file or folder.
     * @param newName     New name for the file or folder.
     * @return Whether the renaming is successful.
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
     * Moves a file or folder.
     *
     * @param fromPath Path of the file or folder to be moved.
     * @param toPath   Destination path.
     * @return Whether the move is successful.
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
