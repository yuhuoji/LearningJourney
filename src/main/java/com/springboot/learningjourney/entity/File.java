package com.springboot.learningjourney.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * TODO 2023-03-29
 */
public class File {
    //
    private String fileId;
    private String fileName;
    private String filePath;
    private String type;
    private Integer size;
    private Date date;
}
