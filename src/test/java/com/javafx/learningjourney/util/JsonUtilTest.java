package com.javafx.learningjourney.util;

import com.javafx.learningjourney.entity.Course;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.util.Map;

class JsonUtilTest {

    static Course course;
    private static Path folderRootPath;

    @BeforeAll
    static void setUp() {
        folderRootPath = RootPathUtil.getFolderRootPath();
        System.out.println("folderRootPath = " + folderRootPath);
        System.out.println("--------------");
        course = new Course();
        course.setName("digit circuit");
        course.setCreditValue(4);
        course.setTotalMark(91d);
        course.setSemester(YearMonth.of(2022, 1));
        course.setTeacher("xxxxxx@xxx.com");
        System.out.println(course);
    }

    @Test
    void testParseJsonFileToMap() {
        Path jsonPath = Paths.get(folderRootPath.toString(), "Course/Digital Circuits/", "course.json");
        System.out.println("jsonPath = " + jsonPath);
        Map<String, Object> jsonMap = JsonUtil.parseJsonFileToMap(jsonPath);
        System.out.println("jsonMap = " + jsonMap);
    }

    @Test
    void testSaveObjectToJsonFile() {
        Path jsonPath = Paths.get(folderRootPath.toString(), "course.json");
        System.out.println("jsonPath = " + jsonPath);
        System.out.println(JsonUtil.saveObjectToJsonFile(course, jsonPath));
    }

    @Test
    void testReadJsonFileToObject() {
        Path jsonPath = Paths.get(folderRootPath.toString(), "course.json");
        System.out.println("jsonPath = " + jsonPath);
        Course course = JsonUtil.readJsonFileToObject(jsonPath, Course.class);
        System.out.println(course);
    }
}