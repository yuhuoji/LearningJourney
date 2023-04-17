package com.javafx.learningjourney.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Internship {
    private String name;
    private String path;
    private String description;
    private String experience;
    private LocalDate startTime;
    private LocalDate endTime;
    private String company;
    private String department;
    private String skill;
}
