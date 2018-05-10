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

    public static String get(String key){
        try{
            proUtils.load(new InputStreamReader(ProUtils.class.getResourceAsStream("/application.properties"),"UTF-8")); //path+"application.properties"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return proUtils.getProperty(key);
    }
}
