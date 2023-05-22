package com.javafx.learningjourney.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

class ResearchTest {
    @Test
    void testCreate() {
        String name = "Research";
        Path path = Paths.get("/path/to/research");
        String description = "This is a research project";

        Research research = new Research();
        research.setName(name);
        research.setPath(path);
        research.setDescription(description);

        Assertions.assertEquals(name, research.getName(), "Name mismatch");
        Assertions.assertEquals(path, research.getPath(), "Path mismatch");
        Assertions.assertEquals(description, research.getDescription(), "Description mismatch");
    }

}