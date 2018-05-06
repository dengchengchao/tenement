package com.dengchengchao.tenement.utils;

import org.slf4j.Logger;

import java.util.List;


/**
 * @Author dengchengchao
 * @Time 2018/5/4
 * @Description
 */
public class LogUtils {

    public static void infoList(Logger logger, List<String> list, String head){
        String lastStr="\n";
        for (String str:list){
            lastStr+=str+"\n";
        }
        logger.info(head+lastStr);
    }
}
