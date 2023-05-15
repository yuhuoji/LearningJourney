package com.javafx.learningjourney.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存类
 * TODO 添加过期时间、清除过期数据
 */
public class Cache {
    private static final Map<String, Object> cacheMap = new HashMap<>();

    public static void put(String key, Object value) {
        cacheMap.put(key, value);
    }

    public static Object get(String key) {
        return cacheMap.get(key);
    }

    public static void remove(String key) {
        cacheMap.remove(key);
    }

    public static boolean containsKey(String key) {
        return cacheMap.containsKey(key);
    }

    public static int size() {
        return cacheMap.size();
    }

    public static void clear() {
        cacheMap.clear();
    }
}
