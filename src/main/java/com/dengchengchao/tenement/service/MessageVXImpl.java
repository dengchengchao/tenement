package com.dengchengchao.tenement.service;

import com.alibaba.fastjson.JSONObject;
import com.dengchengchao.tenement.constist.VX;
import com.dengchengchao.tenement.utils.HttpClientUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;



import java.util.ArrayList;
import java.util.List;

/**
 * @Author dengchengchao
 * @Time 2018/5/6
 * @Description
 */
public class MessageVXImpl implements Message {

    @Override
    public boolean send(String message) {
        return false;
    }


    /**
     * 获取access_token
     */
    private static String getAccessToken() {
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>() {
            {
                add(new BasicNameValuePair("grant_type", "client_credential"));
                add(new BasicNameValuePair("appid",VX.APP_KEY));
                add(new BasicNameValuePair("secret",VX.APP_SECRET));
            }

        };
        String result= HttpClientUtils.get(VX.URL_ACCESS_TOKEN, nameValuePair);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getString("access_token");
    }

    /**
     * 上传消息图
     */
    private static void uploadImage(){
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>() {
            {
                add(new BasicNameValuePair("media", "client_credential"));
            }

        };
    }


    public static void main(String[] args) {
        System.out.println(MessageVXImpl.getAccessToken());
    }
}
