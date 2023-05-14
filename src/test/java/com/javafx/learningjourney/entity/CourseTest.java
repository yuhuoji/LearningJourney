package com.javafx.learningjourney.entity;

import com.javafx.learningjourney.util.JsonUtil;
import com.javafx.learningjourney.util.RootPathUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    public static Path folderRootPath; //root path
    static Course course;

    @BeforeAll
    static void beforeAll() {
        folderRootPath = RootPathUtil.getFolderRootPath();
        System.out.println("rootDirectoryPath = " + folderRootPath);
        course = new Course();
        course.setTotalMark(91d);
        course.setName("digit circuit");
        course.setCreditValue(4);
        course.setSemester(YearMonth.of(2022, 1));
        course.setTeacher("xxxxxx@xxx.com");
    }

    @Test
    void calculateTotalMark() {

        // assertEquals(100, course.calculateTotalMark());
        System.out.println(course);

    }

    @Test
    void createTest() {

        System.out.println(course);
//        System.out.println(folderRootPath.resolve("Course/Digital Circuits/course.json"));
//        JsonUtil.saveObjectToJsonFile(course,folderRootPath.resolve("Course/Digital Circuits/course.json"));
//        Course jsonCourse = JsonUtil.readJsonFileToObject(folderRootPath.resolve("Course/Digital Circuits/course.json"),Course.class);
//        System.out.println(jsonCourse);
    }
}