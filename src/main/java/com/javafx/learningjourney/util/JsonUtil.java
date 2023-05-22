package com.javafx.learningjourney.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    /**
     * Use the Jackson library to parse JSON objects into maps
     *
     * @param jsonFilePath json file path
     * @return Map<key, value> object
     */
    public static Map<String, Object> parseJsonFileToMap(Path jsonFilePath) {
        Map<String, Object> jsonMap = new HashMap<>();
        // 创建一个ObjectMapper对象
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 使用ObjectMapper解析JSON文件为Map<String, Object>对象
            jsonMap = objectMapper.readValue(jsonFilePath.toFile(), new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonMap;
    }

    /**
     * Serialize Java objects and store them in a JSON file
     *
     * @param <T>      java object type
     * @param bean     java bean
     * @param filePath json file path
     * @return true if success, false if fail
     */
    public static <T> boolean saveObjectToJsonFile(T bean, Path filePath) {
        if (bean == null) { //java对象为空
            return false;
        }

        if (!Files.exists(filePath)) { //json文件不存在，则创建json文件
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper(); //jackson序列化java对象
        objectMapper.registerModule(new JavaTimeModule()); // 注册 JavaTimeModule 模块，支持 Java 8 日期时间类型的序列化和反序列化


        File file = new File(filePath.toString());
        try {
            objectMapper.writeValue(file, bean); //将java对象序列化存储到json文件中
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * read a JSON file from a specified location and return a Java object
     *
     * @param <T>     java object type
     * @param filePath json file path
     * @param clazz    java object type
     * @return java object
     */
    public static <T> T readJsonFileToObject(Path filePath, Class<T> clazz) {
        if (!filePath.toFile().exists()) { //json文件不存在
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        File file = new File(filePath.toString());
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
