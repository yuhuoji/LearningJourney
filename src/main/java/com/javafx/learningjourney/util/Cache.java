package com.javafx.learningjourney.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//TODO 添加过期时间、清除过期数据
//HashMao线程不安全
public class Cache {
    private static final Map<String, Object> cacheMap = new ConcurrentHashMap<>();

    public static <T> void put(String key, T value) {
        cacheMap.put(key, value);
    }

    public static <T> T get(String key) {
        return (T) cacheMap.get(key);
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

    public static void printAll() {
        System.out.println("-----------\nCurrent Cache:");
        for (Map.Entry<String, Object> entry : cacheMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("-----------");
    }
}
