package com.javafx.learningjourney.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    @BeforeAll
    static void beforeAll() {
        Cache.put("key1", "value1");
        Cache.put("key2", 1);
        Cache.put("key3", Double.valueOf(2));
    }

    @AfterAll
    static void afterAll() {
        Cache.clear();
    }

    @Test
    void testPut() {
        assertFalse(Cache.containsKey("key4"));
        Cache.put("key4", "value4");
        assertTrue(Cache.containsKey("key4"));
    }

    @Test
    void testGet() {
        assertEquals("value1", Cache.get("key1"));
        assertEquals(1, (Integer) Cache.get("key2"));
        assertEquals(2, (Double) Cache.get("key3"));
    }

    @Test
    void testRemove() {
        assertTrue(Cache.containsKey("key1"));
        Cache.remove("key1");
        assertFalse(Cache.containsKey("key1"));
    }

    @Test
    void testContainsKey() {
        assertTrue(Cache.containsKey("key2"));
        assertFalse(Cache.containsKey("key4"));
    }

    @Test
    void testSize() {
        assertEquals(3, Cache.size());
    }

    @Test
    void testClear() {
        assertEquals(3, Cache.size());
        Cache.clear();
        assertEquals(0, Cache.size());
    }

    @Test
    void testPrintAll() {
        Cache.printAll();
    }

    //ConcurrentHashMap线程安全
    @Test
    void testMultiThread() {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                Cache.put("key1", "value1");
                Cache.put("key2", 1);
                Cache.put("key3", Double.valueOf(2));
                Cache.get("key1");
                Cache.get("key2");
                Cache.get("key3");
                Cache.containsKey("key1");
                Cache.containsKey("key2");
                Cache.containsKey("key3");
                Cache.remove("key1");
                Cache.remove("key2");
                Cache.remove("key3");
                Cache.size();
                Cache.clear();
                Cache.printAll();
            }).start();
        }
    }
}