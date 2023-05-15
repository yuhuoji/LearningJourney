package com.javafx.learningjourney.controller.information;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import com.javafx.learningjourney.entity.Course;
import com.javafx.learningjourney.util.Cache;
import com.javafx.learningjourney.util.JsonUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CourseInformationController {
    private final FileDAO fileDAO;
    @FXML
    private SplitPane mainContent;
    @FXML
    private TreeTableView<Path> treeTableView1;
    @FXML
    private TreeTableColumn<Path, Path> treeTableColumn1;
    @FXML
    private TreeTableView<Path> treeTableView2;
    @FXML
    private TreeTableColumn<Path, Path> treeTableColumn2;
    @FXML
    private TreeTableView<Path> treeTableView3;
    @FXML
    private TreeTableColumn<Path, Path> treeTableColumn3;
    @FXML
    private TreeTableView<Path> treeTableView4;
    @FXML
    private TreeTableColumn<Path, Path> treeTableColumn4;
    @FXML
    private Button checkButton;
    @FXML
    private TableColumn<PropertyEntry, String> nameColumn;
    @FXML
    private TableColumn<PropertyEntry, String> valueColumn;
    @FXML
    private TableView<PropertyEntry> coreModuleInfoTreeView;
    @FXML
    private TableView<Course> otherInfoTreeView;
    @FXML
    private TableColumn<?, ?> otherInfo;

    public CourseInformationController() {
        this.fileDAO = new FileDAOImpl();
    }

    /**
     * TODO @date 2023-05-16 四个创建文件夹
     */
    @FXML
    public void initialize() {
        Cache.put(this.getClass().getSimpleName(), this); //将当前controller的引用放入缓存

        Path currentCoursePath = (Path) Cache.get("currentPath");
        System.out.println("currentCoursePath = " + currentCoursePath);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(currentCoursePath, "*.json")) {
            for (Path filePath : directoryStream) {
                // 在这里进行处理，可以调用 readJsonFileToObject 方法读取 JSON 文件并处理数据
                Map<String, Object> jsonMap = JsonUtil.parseJsonFileToMap(filePath);
                //   System.out.println("jsonMap = " + jsonMap);
                // 对读取的数据进行操作
                loadCourseInfo(jsonMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<TreeTableView<Path>> treeTableViewList = Arrays.asList(treeTableView1, treeTableView2, treeTableView3, treeTableView4);
        List<TreeTableColumn<Path, Path>> treeTableColumnList = Arrays.asList(treeTableColumn1, treeTableColumn2, treeTableColumn3, treeTableColumn4);
        Cache.put("treeTableViewList", treeTableViewList);
        Cache.put("treeTableColumnList", treeTableColumnList);

        loadResources(1);
        loadResources(2);
        loadResources(3);
        loadResources(4);

        Cache.remove("treeTableViewList");
        Cache.remove("treeTableColumnList");
    }

    /**
     * 初始化课程信息
     *
     * @param jsonMap Map<key, value>
     */
    private void loadCourseInfo(Map<String, Object> jsonMap) {
        // 创建一个ObservableList来存储PropertyEntry对象
        ObservableList<PropertyEntry> data = FXCollections.observableArrayList();

        // 遍历jsonMap的键值对
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // 创建PropertyEntry对象并添加到data中
            data.add(new PropertyEntry(key, value));
        }

        //  System.out.println("data = " + data);
        // 将data设置为coreModuleInfoTreeView的数据源
        coreModuleInfoTreeView.setItems(data);

        // 设置 PropertyValueFactory，用于从 PropertyEntry 对象中提取属性名和属性值
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        //coreModuleInfoTreeView.getItems().add(type);
        coreModuleInfoTreeView.refresh();
    }

    /**
     * 加载页面资源:学习资料，课程笔记，课程作业，实验项目等
     *
     * @param i 1:学习资料，2:课程笔记，3:课程作业，4:实验项目
     */
    private void loadResources(int i) {
        String resourceStr;
        TreeTableView<Path> treeTableView;
        TreeTableColumn<Path, Path> treeTableColumn;
        switch (i) {
            case 2:
                resourceStr = "Course notes";
                treeTableView = treeTableView2;
                treeTableColumn = treeTableColumn2;
                break;
            case 3:
                resourceStr = "Course work";
                treeTableView = treeTableView3;
                treeTableColumn = treeTableColumn3;
                break;
            case 4:
                resourceStr = "Experimental project";
                treeTableView = treeTableView4;
                treeTableColumn = treeTableColumn4;
                break;
            default:
                resourceStr = "Learning materials";
                treeTableView = treeTableView1;
                treeTableColumn = treeTableColumn1;
                break;
        }
        Path currentCoursePath = (Path) Cache.get("currentPath");
        Path currentResource = currentCoursePath.resolve(resourceStr);
        System.out.println("currentResource = " + currentResource);
        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFilesInCurrentLevel(currentResource);

        if (rootItem == null) {
            System.out.println("rootItem is null");
            rootItem = new TreeItem<>(currentResource.getFileName()); // 创建根节点
        } else {
            System.out.println("rootItem = " + rootItem);
        }
        rootItem.setExpanded(true); //set the root expanded
        treeTableView.setRoot(rootItem);
        treeTableView.setShowRoot(false);

        treeTableColumn.setCellValueFactory(param -> param.getValue().valueProperty()); //为列设置单元格值工厂，绑定值

        ContextMenu contextMenu = new ContextMenu();
        MenuItem addItem = new MenuItem("add"); //设置新增文件的方法
        addItem.setOnAction(event -> {
            System.out.println("add");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the file you want to add");// 设置对话框的标题
            fileChooser.setInitialDirectory(currentResource.toFile()); // 设置初始目录
            File selectedFile = fileChooser.showOpenDialog((Stage) Cache.get("stage"));// 显示文件选择对话框并获取所选文件
            if (selectedFile != null) { // 检查用户是否选择了文件
                String filePath = selectedFile.getAbsolutePath();
                System.out.println("所选文件路径：" + filePath);
                fileDAO.moveFileOrFolder(Paths.get(filePath), currentResource); //move the file to the current resource folder
            } else {
                System.out.println("未选择文件");
            }
            loadResources(i); //FIXME @date 2023-05-15 刷新treeTableView，重新加载资源
        });
        MenuItem deleteItem = new MenuItem("delete");
        deleteItem.setOnAction(event -> { //设置删除文件的方法
            TreeItem<?> selectedItem = treeTableView.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                return;
            }
            TreeItem<?> parentItem = selectedItem.getParent();
            if (parentItem != null) {
                Object deleteFileName = treeTableColumn.getCellData((TreeItem<Path>) selectedItem);
//                System.out.println("deleteFileName = " + deleteFileName);
//                System.out.println("currentResource = " + currentResource.toString() + ", resourceStr = " + resourceStr);
                Path deletePath = Paths.get(currentResource.toString());
//                System.out.println("deletePath = " + deletePath);
                fileDAO.deleteFileOrFolder(deletePath, deleteFileName.toString());
                parentItem.getChildren().remove(selectedItem);
            } else {
                treeTableView.setRoot(null);
            }

        });


        treeTableView.setContextMenu(contextMenu); // 为TreeTableView绑定ContextMenu

        // 设置鼠标在单元格上右键单击时打开完整的菜单（包括add和delete方法）
        treeTableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (treeTableView.getSelectionModel().isEmpty()) {
                    // 空白处右键单击，只显示add方法的菜单
                    contextMenu.getItems().setAll(addItem);
                } else {
                    // 单元格右键单击，根据数据是否存在显示不同的菜单
                    TreeItem<Path> selectedItem = treeTableView.getSelectionModel().getSelectedItem();
                    Path rowData = selectedItem.getValue();
                    System.out.println("Clicked Row Data: " + rowData);

                    String filePath = currentResource.resolve(rowData).toString();
                    System.out.println("filePath = " + filePath);

                    File file = new File(filePath);
                    if (file.exists()) {
                        contextMenu.getItems().setAll(addItem, deleteItem);
                    } else {
                        contextMenu.getItems().setAll(addItem);
                    }
                }

                contextMenu.show(treeTableView, event.getScreenX(), event.getScreenY());
            }
        });



        treeTableColumn.setCellFactory(column -> { // 为列设置单元格工厂，设置方法
            TreeTableCell<Path, Path> cell = new TreeTableCell<Path, Path>() { //为treeTableColumn设置自定义的TreeTableCell
                @Override
                protected void updateItem(Path item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : getString());
                }

                private String getString() {
                    return getItem() == null ? "" : getItem().toString();
                }
            };

            cell.setOnMouseClicked(event -> { // 添加点击事件处理程序
                if (cell.isEmpty()) { //为空则返回
                    return;
                }
                if (event.getClickCount() == 1) { //单击选中

                }
                if (event.getClickCount() == 2) { //双击打开
                    TreeItem<Path> treeItem = cell.getTreeTableRow().getTreeItem();
                    if (treeItem != null) {
                        Path rowData = treeItem.getValue();
                        System.out.println("Clicked Row Data: " + rowData);

                        String filePath = currentResource.resolve(rowData).toString();
                        System.out.println("filePath = " + filePath);

                        File file = new File(filePath);   // 创建一个File对象
                        if (file.exists()) { // 检查文件是否存在
                            Desktop desktop = Desktop.getDesktop(); // 获取Desktop实例
                            try {
                                desktop.open(file); // 打开文件
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("File does not exist.");
                        }
                    }
                }

            });

            return cell;
        });

    }

    /**
     * TODO
     *
     * @param actionEvent ActionEvent
     */
    @FXML
    public void checkCourseScore(ActionEvent actionEvent) {
        System.out.println("checkCourseScore");
    }


    /**
     * PropertyEntry 类用于存储属性名和属性值 key-value
     */
    protected class PropertyEntry {
        private final String name;
        private final Object value;

        public PropertyEntry(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }

        @Override
        public String toString() {
            return name + " : " + value;
        }
    }
}
