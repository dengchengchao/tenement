package com.dengchengchao.tenement.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author dengchengchao
 * @Time 2018/5/9
 * @Description
 */
public class StringUtils {


    /**
     * @param semicolonWord 以分号(不区分中英文)隔开的句子
     * @return 以分号分割开的List
     */
    public static List<String> semicolonWordToList(String semicolonWord) {
        if (null != semicolonWord) {
            semicolonWord = semicolonWord.replace("；", ";");
            return Arrays.asList(semicolonWord.split(";"));
        }
        return null;
    }



    /**
     *  输入 A;B;C & D;E;F
     *  输出 [[A,B,C],[D,E,F]]
     *
     * @param andSymbolWord 以&符号分割开的的句子
     * @return  List中包含一个List
     */
    public static List<List<String>> andSymbolWordToList(String andSymbolWord) {
        List<List<String>> result = new ArrayList<>();
        if (null == andSymbolWord) return result;
        List<String> lists = Arrays.asList(andSymbolWord.split("&"));
        for (String str : lists) {
            result.add(semicolonWordToList(str));
        }
        return result;
    }

}
