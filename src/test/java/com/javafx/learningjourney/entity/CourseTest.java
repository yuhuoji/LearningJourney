package com.javafx.learningjourney.entity;

import com.javafx.learningjourney.util.JsonUtil;
import com.javafx.learningjourney.util.RootPathUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.YearMonth;

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
    void testCalculateTotalMark() {

        course.setNormalMark(80.0);
        course.setNormalMarkWeight(0.3);
        course.setExperimentalMark(90.0);
        course.setExperimentalMarkWeight(0.2);
        course.setFinalMark(85.0);
        course.setFinalMarkWeight(0.5);

        double expectedTotalMark = 84.5;
        double actualTotalMark = course.calculateTotalMark();

        Assertions.assertEquals(expectedTotalMark, actualTotalMark, 0.01, "Total mark calculation failed");
    }

    @Test
    void testCreate() {
        System.out.println(course);
        System.out.println(folderRootPath.resolve("Course/Digital Circuits/course.json"));
        JsonUtil.saveObjectToJsonFile(course, folderRootPath.resolve("Course/Digital Circuits/course.json"));
        Course jsonCourse = JsonUtil.readJsonFileToObject(folderRootPath.resolve("Course/Digital Circuits/course.json"), Course.class);
        System.out.println("jsonCourse = " + jsonCourse);
    }
}