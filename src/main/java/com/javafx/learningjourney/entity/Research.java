package com.javafx.learningjourney.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Research {
    private String name;
    private String path;
    private String description;
}
