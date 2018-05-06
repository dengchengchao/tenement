package com.dengchengchao.tenement;

import com.dengchengchao.tenement.service.PushService;

/**
 * @Author dengchengchao
 * @Time 2018/5/6
 * @Description
 */
public class Main {

    public static void main(String[] args) {
        PushService pushService=new PushService();
        pushService.run();
    }
}
