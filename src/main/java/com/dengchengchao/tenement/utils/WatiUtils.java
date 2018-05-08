package com.dengchengchao.tenement.utils;

/**
 * @Author dengchengchao
 * @Time 2018/5/8
 * @Description
 */
public class WatiUtils {

    /**
     * Slpee一段时间
     * @param second 秒
     */
    public static void wait(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
