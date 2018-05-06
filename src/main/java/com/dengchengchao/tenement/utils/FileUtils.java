package com.dengchengchao.tenement.utils;

import java.io.File;
import java.io.FileWriter;

/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public class FileUtils {

    public static void write(String filePath, String content) {
        try (FileWriter fileWriter = new FileWriter(filePath,true)) {
            fileWriter.append(content);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建文件夹
     */
    public static boolean makeDir(String filePath){
        File file=new File(filePath);
        return file.mkdirs();
    }
}
