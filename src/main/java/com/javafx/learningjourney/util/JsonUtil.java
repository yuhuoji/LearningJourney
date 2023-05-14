package com.javafx.learningjourney.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    /**
     * 使用 Jackson 库将 JSON 对象解析为 Map
     *
     * @param jsonFilePath json文件路径
     * @return Map<key, value>
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
     * 将java对象序列化存储到json
     *
     * @param bean     java对象
     * @param filePath json文件路径
     * @return 序列化和存储是否成功
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
     * 读取指定位置的json文件，返回java对象
     *
     * @param filePath json文件路径
     * @param clazz    目标反序列化的java对象类型
     * @param <T>      java对象类型
     * @return 反序列化后的java对象
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
