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
        if (null==list)return;
        String lastStr="\n";
        for (String str:list){
            lastStr+=str+"\n";
        }
        logger.info(head+lastStr);
    }

    public static void infoListWithList(Logger logger, List<List<String>> list, String head){
        if (null==list)return;
        for (List<String> lists:list){
            infoList(logger,lists,head);
        }
    }
}
