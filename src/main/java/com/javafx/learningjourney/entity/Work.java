package com.javafx.learningjourney.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

/**
 * 承担角色
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Work {
    private String name;
    private Path path;
    private String description;
    private String experience;
}
