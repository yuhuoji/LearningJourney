package com.javafx.learningjourney.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void calculateTotalMark() {
        Course course = new Course();
        course.setTotalMark(100d);
        course.setFinalMark(10.0);
        course.setName("digit circuit");
        assertEquals(100, course.calculateTotalMark());

        System.out.println(course.toString());

    }
}