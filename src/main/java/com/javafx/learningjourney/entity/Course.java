package com.javafx.learningjourney.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.time.YearMonth;

//lombok插件
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private String name;
    private Path path;
    private String description;

    private Double normalMark; //TODO how to accept int Integer double Double
    private Double normalMarkWeight;
    private Double experimentalMark;
    private Double experimentalMarkWeight;
    private Double finalMark;
    private Double finalMarkWeight;
    private Double totalMark;

    private Integer creditValue;
    private YearMonth semester;
    private String teacher;

    /**
     * calculate total mark
     * @return total mark, -1 represents error
     */
    public Double calculateTotalMark() {
        if (normalMark == null || experimentalMark == null || finalMark == null
                ||
                normalMark < 0 || experimentalMark < 0 || finalMark < 0
                ||
                normalMark > 100 || experimentalMark > 100 || finalMark > 100) { //mark error
            return -1.0;
        }

        if (normalMarkWeight == null || experimentalMarkWeight == null || finalMarkWeight == null
                ||
                normalMarkWeight < 0 || experimentalMarkWeight < 0 || finalMarkWeight < 0
                ||
                normalMarkWeight > 1 || experimentalMarkWeight > 1 || finalMarkWeight > 1
                ||
                normalMarkWeight + experimentalMarkWeight + finalMarkWeight != 1) { //weight error
            return -1.0;
        }

        return normalMark * normalMarkWeight + experimentalMark * experimentalMarkWeight + finalMark * finalMarkWeight;
    }
}
