package com.javafx.learningjourney.controller.information;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import com.javafx.learningjourney.entity.Course;
import com.javafx.learningjourney.util.CustomEvent;
import com.javafx.learningjourney.util.JsonUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static com.javafx.learningjourney.JavaFXApplication.folderRootPath;
import static com.javafx.learningjourney.controller.CourseController.currentCourse;

public class CourseInformationController {
    private final FileDAO fileDAO;
    @FXML
    public TreeTableView<Path> learningMaterialsTreeTableView;
    private String course; //current course
    @FXML
    private TitledPane titledPane1;
    @FXML
    private SplitPane mainContent;
    @FXML
    private Button checkButton;
    @FXML
    private TableColumn<PropertyEntry, String> nameColumn;
    @FXML
    private TableColumn<PropertyEntry, String> valueColumn;
    @FXML
    private VBox courseInformationVBoxRight;
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
     * TODO
     */
    @FXML
    public void initialize() {
        course = currentCourse; //set current course

        //Path folderPath = folderRootPath.resolve("Course/Digital Circuits"); //TODO
        Path currentCoursePath = Paths.get(folderRootPath.toString(), "Course", course);//TODO
        System.out.println("currentCoursePath = " + currentCoursePath);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(currentCoursePath, "*.json")) {
            for (Path filePath : directoryStream) {
                // 在这里进行处理，可以调用 readJsonFileToObject 方法读取 JSON 文件并处理数据
                Map<String, Object> jsonMap = JsonUtil.parseJsonFileToMap(filePath);
                System.out.println("jsonMap = " + jsonMap);
                // 对读取的数据进行操作
                setCourseInfo(jsonMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param jsonMap Map<key, value>
     */
    private void setCourseInfo(Map<String, Object> jsonMap) {
        // 创建一个ObservableList来存储PropertyEntry对象
        ObservableList<PropertyEntry> data = FXCollections.observableArrayList();

        // 遍历jsonMap的键值对
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // 创建PropertyEntry对象并添加到data中
            data.add(new PropertyEntry(key, value));
        }

        System.out.println("data = " + data);
        // 将data设置为coreModuleInfoTreeView的数据源
        coreModuleInfoTreeView.setItems(data);

        // 设置 PropertyValueFactory，用于从 PropertyEntry 对象中提取属性名和属性值
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        //coreModuleInfoTreeView.getItems().add(type);
        coreModuleInfoTreeView.refresh();
    }

    /**
     * 加载页面资源，学习资料，课程笔记，课程作业，实验项目等
     */
    private void loadResources() {
        //Learning materials
       /* Path folderPath = Paths.get(folderRootPath.toString(), "Course", course, "Learning materials");
        TreeItem<Path> rootItem = fileDAO.createTreeOfAllFoldersInCurrentLevel(folderPath);
        if (rootItem == null) {
            System.out.println("rootItem is null");
            rootItem = new TreeItem<>(folderRootPath.getFileName()); // 创建根节点
        } else {
            System.out.println("rootItem = " + rootItem);
        }
        rootItem.setExpanded(true); //set the root expanded*/

    }

    /**
     * TODO
     *
     * @param actionEvent ActionEvent
     */
    @FXML
    public void checkCourseScore(ActionEvent actionEvent) {

    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // PropertyEntry 类用于存储属性名和属性值
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
