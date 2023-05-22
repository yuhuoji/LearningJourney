package com.javafx.learningjourney.entity;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkTest {
    @Test
    void testCreate() {
        String name = "Work";
        String description = "This is a work project";
        String experience = "This is a work experience";

        Work work = new Work();
        work.setName(name);
        work.setDescription(description);
        work.setExperience(experience);

        Assertions.assertEquals(name, work.getName(), "Name mismatch");
        Assertions.assertEquals(description, work.getDescription(), "Description mismatch");
        Assertions.assertEquals(experience, work.getExperience(), "Experience mismatch");
    }
}