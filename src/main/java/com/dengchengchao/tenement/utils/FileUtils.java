package com.dengchengchao.tenement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 写文件
     */
    public static void write(String filePath, String content) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8")) {
            writer.append(content);
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    /**
     * 读文件
     */
    public static List<String> read(String filePath) {
        List<String> result = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr)
        ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 创建文件夹
     */
    public static boolean makeDir(String filePath) {
        File file = new File(filePath);
        return file.mkdirs();
    }
}
