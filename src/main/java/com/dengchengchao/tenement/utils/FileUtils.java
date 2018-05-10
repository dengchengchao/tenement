package com.dengchengchao.tenement.utils;

import com.dengchengchao.tenement.consist.Html;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.HTML;
import java.io.*;
import java.util.*;

/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static final String HTML_HEAD = "<!DOCTYPE html>";

    /**
     * 写文件
     */
    public static void write(String filePath, String content,boolean append) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath, append), "UTF-8")) {

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
        if (!exist(filePath)) return result;
        try (
                FileInputStream fis = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 创建新的文件夹
     * @param filePath 路劲
     * @return 如果文件已存在或者创建失败返回false
     */
    public static boolean makeDir(String filePath) {
        File file = new File(filePath);
        return !file.exists() && file.mkdirs();
    }


    /**
     *  写入 html body 中
     * @param filePath  文件路径
     * @param content  内容
     * @return 原本的文件是否存在
     */
    public static void  writeHtmlFile(String filePath, String content) {
        TenList<String> oldList = new TenList<>(read(filePath));
        StringBuilder contentAll= new StringBuilder();
        //原本文件不是html
        if (!oldList.contains(Html.HEAD_LINE)){
            contentAll.append(Html.HEAD).append("\r\n");
            for (String str:oldList){
                contentAll.append(str).append("\r\n");
            }
            contentAll.append(content).append("\r\n");
            contentAll.append(Html.END);
            write(filePath,contentAll.toString(),true);
        }else{
            //html尾两行
            oldList.remove(oldList.size()-1);
            oldList.remove(oldList.size()-1);
            oldList.add(content);
            oldList.add(Html.END);
            write(filePath,oldList.toString(),false);
        }

    }

    /**
     * 判断文件是否存在
     */
    public static boolean exist(String filePath){
        return new File(filePath).exists();
    }


    /**
     * 装换为url路径
     */
    public static String toHtmlPath(String filePath){
        if (null!=filePath){
            return  filePath.replace(File.separator,"/").replace("\\","/");
        }
        return null;
    }

}
