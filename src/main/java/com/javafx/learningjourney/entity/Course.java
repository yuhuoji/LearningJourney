package com.javafx.learningjourney.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

//lombok插件
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String name;
    private String path;
    private String description;
    private Integer mark;
    private Integer creditValue;
    private YearMonth semester;
    private String teacher;

}
