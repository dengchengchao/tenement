package com.dengchengchao.tenement.utils;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public class ProUtils {

    private static Properties proUtils =new Properties();

    static {
        try{
            String path=ProUtils.class.getResource("/").getPath();
            proUtils.load(new InputStreamReader(ProUtils.class.getClassLoader().getResourceAsStream("application.properties"),"UTF-8")); //path+"application.properties"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return proUtils.getProperty(key);
    }
}
