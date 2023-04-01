package com.javafx.learningjourney.util;

import org.junit.jupiter.api.Test;


class RootPathUtilTest {
    RootPathUtil rootPathUtil = new RootPathUtil();

    @Test
    public void test() {
        System.out.println("Path = " + rootPathUtil.getRootPath());

    }

}