package com.javafx.learningjourney.util;

import com.javafx.learningjourney.entity.Course;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {

    private static Path testFolderPath;

    @BeforeAll
    static void setUp() {
        System.out.println("-------");
        testFolderPath = Paths.get("E:/Workspace");
    }

    @Test
    void saveObjectToJsonFile() {

        Course course = new Course("name", Paths.get("C:/"), "description", (double) 90, (double) 0.25, (double) 95, (double) 0.25, (double) 90, (double) 0.5, null, 4, YearMonth.of(2023, 4), "teacher@edu.cn");

        System.out.println(course);

        Path jsonPath = Paths.get(testFolderPath.toString(), "course.json");
        System.out.println("jsonPath = " + jsonPath);
        System.out.println(JsonUtil.saveObjectToJsonFile(course, jsonPath));
    }

    @Test
    void readJsonFileToObject() {
        Path jsonPath = Paths.get(testFolderPath.toString(), "course.json");
        System.out.println("jsonPath = " + jsonPath);
        Course course = JsonUtil.readJsonFileToObject(jsonPath, Course.class);
        System.out.println(course);
    }
}