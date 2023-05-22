package com.javafx.learningjourney.util;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RootPathUtil {
    /**
     * Retrieve the path of the project's root directory
     * @return the path of the project's root directory
     */
    public static Path getRootPath() {
        // 获取打包后JAR文件所在的目录位置
        Path jarPath = null;
        try {
            jarPath = Paths.get(RootPathUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
//        System.out.println("jarPath = "+ jarPath);
        Path projectDir = jarPath.getParent();

        // 如果不是JAR文件运行，则获取项目根目录的相对路径
        if (projectDir.toString().endsWith(".jar")) {
            // JAR文件运行时，将项目根目录设置为JAR文件所在目录
            projectDir = projectDir.getParent();
        }
        return projectDir;
    }

    /**
     * Retrieve the root directory of the project file storage
     * @return the root directory of the project file storage
     */
    public static Path getFolderRootPath(){
        return Paths.get(getRootPath().toString(),"LearningJourneyFiles");
    }
}
