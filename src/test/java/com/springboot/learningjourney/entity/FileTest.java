package com.springboot.learningjourney.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {

    @BeforeEach
    void setUp() {
        System.out.println("test start");
    }

    @AfterEach
    void tearDown() {
        System.out.println("test end");
    }

    @Test
    void testToString() {
        File file = new File();
        file.setFileName("aaaaa");
        System.out.println(file.toString());
    }
}