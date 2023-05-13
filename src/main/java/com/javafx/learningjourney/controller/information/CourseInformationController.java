package com.javafx.learningjourney.controller.information;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import com.javafx.learningjourney.entity.Course;
import com.javafx.learningjourney.util.JsonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import static com.javafx.learningjourney.JavaFXApplication.folderRootPath;

public class CourseInformationController {
    private final FileDAO fileDAO;
    @FXML
    private SplitPane mainContent;

    @FXML
    private VBox dropdownBox1;

    @FXML
    private Button dropdownButton1;

    @FXML
    private VBox dropdownBox2;

    @FXML
    private Button dropdownButton2;

    @FXML
    private VBox dropdownBox3;

    @FXML
    private Button dropdownButton3;

    @FXML
    private VBox dropdownBox4;

    @FXML
    private Button dropdownButton4;

    @FXML
    private Button checkButton;

    @FXML
    private VBox courseInformationVBoxRight;

    @FXML
    private TableView<?> coreModuleInfoTreeView;

    @FXML
    private TableView<?> otherInfoTreeView;

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
        Course jsonCourse = JsonUtil.readJsonFileToObject(folderRootPath.resolve("Course/Digital Circuits/course.json"),Course.class);

    }

    @FXML
    public void checkCourseScore(ActionEvent actionEvent) {

    }
}
