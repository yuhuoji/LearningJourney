package com.javafx.learningjourney.util;

import org.junit.jupiter.api.Test;

class RootPathUtilTest {

    @Test
    public void testGetRootPath() {
        System.out.println("Path = " + RootPathUtil.getRootPath());
    }

    @Test
    void testGetFolderRootPath() {
        System.out.println("Path = " + RootPathUtil.getFolderRootPath());
    }
}