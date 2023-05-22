package com.javafx.learningjourney.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

class InternshipTest {
    @Test
    void testCreate() {
        String name = "Internship";
        Path path = Paths.get("/path/to/internship");
        String description = "This is an internship";
        String experience = "Worked on various projects";
        LocalDate startTime = LocalDate.of(2022, 1, 1);
        LocalDate endTime = LocalDate.of(2022, 12, 31);
        String company = "ABC Company";
        String department = "Software Development";
        String skill = "Java, Python, SQL";

        Internship internship = new Internship();
        internship.setName(name);
        internship.setPath(path);
        internship.setDescription(description);
        internship.setExperience(experience);
        internship.setStartTime(startTime);
        internship.setEndTime(endTime);
        internship.setCompany(company);
        internship.setDepartment(department);
        internship.setSkill(skill);

        Assertions.assertEquals(name, internship.getName(), "Name mismatch");
        Assertions.assertEquals(path, internship.getPath(), "Path mismatch");
        Assertions.assertEquals(description, internship.getDescription(), "Description mismatch");
        Assertions.assertEquals(experience, internship.getExperience(), "Experience mismatch");
        Assertions.assertEquals(startTime, internship.getStartTime(), "Start time mismatch");
        Assertions.assertEquals(endTime, internship.getEndTime(), "End time mismatch");
        Assertions.assertEquals(company, internship.getCompany(), "Company mismatch");
        Assertions.assertEquals(department, internship.getDepartment(), "Department mismatch");
        Assertions.assertEquals(skill, internship.getSkill(), "Skill mismatch");
    }
}